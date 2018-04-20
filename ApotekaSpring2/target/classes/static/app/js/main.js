var apotekaApp = angular.module("apotekaApp", ['ngRoute']);

apotekaApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/lekovi.html'
    }).when('/lekovi/edit/:id',{
        templateUrl: '/app/html/partial/edit-lek.html'
    }).when('/lekovi/kupovina/:id',{
        templateUrl: '/app/html/partial/kupovina-leka.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

apotekaApp.controller("lekoviCtrl", function($scope, $http, $location){

	var baseUrlApoteke = "/api/apoteke";
    var baseUrlProizvodjaci = "/api/proizvodjaci";
    var baseUrlLekovi = "/api/lekovi";

      
    $scope.pageNum = 0;
    $scope.totalPages = 1;

    $scope.apoteke = [];
    $scope.proizvodjaci = [];
    $scope.lekovi = [];
    
    $scope.noviLek = {};
    $scope.noviLek.naziv = "";
    $scope.noviLek.generickiNaziv = "";
    $scope.noviLek.kolicinaNaStanju = "";
    $scope.noviLek.cena = "";
    $scope.noviLek.apotekaId = "";
    $scope.noviLek.proizvodjacId = "";

    $scope.trazeniLek = {};
    $scope.trazeniLek.naziv = '';
    $scope.trazeniLek.nazivGenNaziv = '';
    $scope.trazeniLek.apotekaId = '';

    var getLekovi = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazeniLek.nazivGenNaziv != ""){
            config.params.nazivGenNaziv = $scope.trazeniLek.nazivGenNaziv;
        }

        if($scope.trazeniLek.apotekaId != ""){
            config.params.apotekaId = $scope.trazeniLek.apotekaId;
        
        }
               
        $http.get(baseUrlLekovi, config)
            .then(function success(data){
                $scope.lekovi = data.data;
                $scope.totalPages = data.headers('totalPages');

            })
    
    };
  
    var getApoteke = function(){

        $http.get(baseUrlApoteke)
            .then(function success(data){
                $scope.apoteke = data.data;
            });

    };

    var getProizvodjaci = function(){

        $http.get(baseUrlProizvodjaci)
            .then(function success(data){
                $scope.proizvodjaci = data.data;
            });

    };

    
    getApoteke();
    getProizvodjaci();
    getLekovi();

    $scope.ponisti = function(){
        $scope.trazeniLek.nazivGenNaziv = '';
        $scope.trazeniLek.apotekaId = '';
        getLekovi(); 
    }

    $scope.go = function(direction){
        $scope.pageNum = $scope.pageNum + direction;
        getLekovi();
        
    };

    $scope.dodaj = function(){
        $http.post(baseUrlLekovi, $scope.noviLek)
            .then(
            	function success(data){
	            	getLekovi();
	
	            	$scope.noviLek = {};
            	},
            	function error(data){
            		alert("Neuspešno dodavanje leka!");
            	}
            );
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getLekovi();
    }

    $scope.izmeni = function(id){
        $location.path('/lekovi/edit/' + id);
    }

    $scope.kupi = function(id){
        $location.path('/lekovi/kupovina/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlLekovi + "/" + id).then(
            function success(data){
            	getLekovi();
            },
            function error(data){
                alert("Neuspešno brisanje!");
            }
        );
    }
    
    
});

apotekaApp.controller("editLekCtrl", function($scope, $http, $routeParams, $location){

    var baseUrlLekovi = "/api/lekovi";

    $scope.stariLek = null;

    var getStariLek = function(){

        $http.get(baseUrlLekovi + "/" + $routeParams.id)
            .then(
            	function success(data){
            		$scope.stariLek = data.data;
            	},
            	function error(data){
            		alert("Neušpesno dobavljanje leka.");
            	}
            );

    }
    getStariLek();
    
    $scope.izmeni = function(){
        $http.put(baseUrlLekovi + "/" + $scope.stariLek.id, $scope.stariLek)
            .then(
        		function success(data){
        			alert("Uspešno izmenjen lek!");
        			$location.path("/");
        		},
        		function error(data){
        			alert("Neuspešna izmena leka.");
        		}
            );
    }
});

apotekaApp.controller("kupiLekCtrl", function($scope, $http, $routeParams, $location){

    var baseUrlLekovi = "/api/lekovi";

    $scope.lekZaKupovinu = {};
    $scope.kupovina = {};
    $scope.kupovina.brojKomada = '';

    var getLekZaKupovinu = function(){

        $http.get(baseUrlLekovi + "/" + $routeParams.id)
            .then(
                function success(data){
                    $scope.lekZaKupovinu = data.data;
                },
                function error(data){
                    alert("Neušpesno dobavljanje leka.");
                }
            );

    }
    getLekZaKupovinu();

     
    $scope.kupi = function(){

        $http.post(baseUrlLekovi + "/" + $routeParams.id + "/kupovina", $scope.kupovina.brojKomada).then(
                function success(data){
                    alert("Uspešno kupljen lek!");
                    $location.path("/");

                },
                function error(data){
                    console.log(id);
                    // console.log($scope.kupovina.brojKomada);
                    alert("Lek nije kupljen.");
                }
            );
    }
});












