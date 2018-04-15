var utakmicaApp = angular.module("utakmicaApp", ['ngRoute']);

utakmicaApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/pretraga.html'
    }).when('/igraci/dodaj/',{
        templateUrl: '/app/html/partial/dodaj-igraca.html'
    }).when('/igraci/edit/:id',{
        templateUrl: '/app/html/partial/edit-igrac.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

utakmicaApp.controller("pretragaCtrl", function($scope, $http, $location){

	var baseUrlTimovi = "/api/timovi";
    var baseUrlPozicije = "/api/pozicije";
    var baseUrlIgraci = "/api/igraci";

      
    $scope.pageNum = 0;
    $scope.totalPages = 1;

    $scope.timovi = [];
    $scope.pozicije = [];
    $scope.igraci = [];
    
    $scope.noviIgrac = {};
    $scope.noviIgrac.imeIPrezime = "";
    $scope.noviIgrac.broj = "";
    $scope.noviIgrac.pozicijaId = "";
    $scope.noviIgrac.timId = "";

    $scope.trazeniIgrac = {};
    $scope.trazeniIgrac.imeIPrezime = '';
    $scope.trazeniIgrac.broj = '';
    $scope.trazeniIgrac.timId = '';

    var getIgraci = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

         if($scope.trazeniIgrac.imeIPrezime != ""){
            config.params.imeIPrezime = $scope.trazeniIgrac.imeIPrezime;
        }

        if($scope.trazeniIgrac.broj != ""){
            config.params.broj = $scope.trazeniIgrac.broj;
        }

        if($scope.trazeniIgrac.timId != ""){
            config.params.timId = $scope.trazeniIgrac.timId;
        
        }
               
        $http.get(baseUrlIgraci, config)
            .then(function success(data){
                $scope.igraci = data.data;
                $scope.totalPages = data.headers('totalPages');

            })
    
    };
  
    var getTimovi = function(){

        $http.get(baseUrlTimovi)
            .then(function success(data){
                $scope.timovi = data.data;
            });

    };

    var getPozicije = function(){

        $http.get(baseUrlPozicije)
            .then(function success(data){
                $scope.pozicije = data.data;
            });

    };

    
    getTimovi();
    getPozicije();
    getIgraci();

    $scope.go = function(direction){
        $scope.pageNum = $scope.pageNum + direction;
        getIgraci();
        
    };

    $scope.dodaj = function(){
         $location.path('/igraci/dodaj/');
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getIgraci();
    }

    $scope.izmeni = function(id){
        $location.path('/igraci/edit/' + id);
    }

    $scope.faul = function(id){
        $http.post(baseUrlIgraci + '/' + id + '/faul').then(
            function success(data){
                getIgraci();
            }
        );
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlIgraci + "/" + id).then(
            function success(data){
            	getIgraci();
            },
            function error(data){
                alert("Neuspešno brisanje!");
            }
        );
    }
    
    
});

utakmicaApp.controller("dodajIgracaCtrl", function($scope, $http, $location){

    var baseUrlTimovi = "/api/timovi";
    var baseUrlPozicije = "/api/pozicije";
    var baseUrlIgraci = "/api/igraci";

    $scope.timovi = [];
    $scope.pozicije = [];
    $scope.igraci = [];
    
    var getTimovi = function(){

        $http.get(baseUrlTimovi)
            .then(function success(data){
                $scope.timovi = data.data;
            });

    };

    var getPozicije = function(){

        $http.get(baseUrlPozicije)
            .then(function success(data){
                $scope.pozicije = data.data;
            });

    };
    
    getTimovi();
    getPozicije();

    $scope.dodajIgraca = function(){
        $http.post(baseUrlIgraci, $scope.noviIgrac)
            .then(
                function success(data){
                    alert("Uspešno dodavanje igrača!")
                    $location.path('/');
                },
                function error(data){
                    alert("Neuspešno dodavanje igrača! Tim može imati najviše 12 igrača.");
                }
            );
    };
});

utakmicaApp.controller("editIgracCtrl", function($scope, $http, $routeParams, $location){

    var baseUrlIgraci = "/api/igraci";

    $scope.stariIgrac = null;

    var getStariIgrac = function(){

        $http.get(baseUrlIgraci + "/" + $routeParams.id)
            .then(
            	function success(data){
            		$scope.stariIgrac = data.data;
            	},
            	function error(data){
            		alert("Neušpesno dobavljanje igrača.");
            	}
            );

    }
    getStariIgrac();
    
    $scope.izmeni = function(){
        $http.put(baseUrlIgraci + "/" + $scope.stariIgrac.id, $scope.stariIgrac)
            .then(
        		function success(data){
        			alert("Uspešno izmenjen igrač!");
        			$location.path("/");
        		},
        		function error(data){
        			alert("Neuspešna izmena igrača.");
        		}
            );
    }
});














