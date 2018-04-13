var stanicaApp = angular.module("stanicaApp", ['ngRoute']);

stanicaApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',{
        templateUrl: '/app/html/partial/linije.html'
    }).when('/linije/edit/:id',{
        templateUrl: '/app/html/partial/edit-linija.html'
    }).when('/prevoznici/dodaj/:id',{
        templateUrl: '/app/html/partial/dodavanje-prevoznika.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

stanicaApp.controller("linijeCtrl", function($scope, $http, $location){

	var baseUrlPrevoznici = "/api/prevoznici";
    var baseUrlLinije = "/api/linije";
    
    $scope.pageNum = 0;
    $scope.totalPages = 0;

    $scope.prevoznici = [];
    $scope.linije = [];

    $scope.novaLinija = {};
    $scope.novaLinija.brojMesta = "";
    $scope.novaLinija.cenaKarte = "";
    $scope.novaLinija.vremePolaska = "";
    $scope.novaLinija.destinacija = "";
    $scope.novaLinija.prevoznikId = "";


    $scope.trazenaLinija = {};
    $scope.trazenaLinija.destinacija = "";
    $scope.trazenaLinija.maxCena = "";
    $scope.trazenaLinija.prevoznikId = "";

    var getLinije = function(){

        var config = {params: {}};

        config.params.pageNum = $scope.pageNum;

        if($scope.trazenaLinija.destinacija != ""){
            config.params.destinacija = $scope.trazenaLinija.destinacija;
        }
        
        if($scope.trazenaLinija.maxCena != ""){
            config.params.maxCena = $scope.trazenaLinija.maxCena;
        }

        if($scope.trazenaLinija.prevoznikId != ""){
            config.params.prevoznikId = $scope.trazenaLinija.prevoznikId;
        }

       $http.get(baseUrlLinije, config)
            .then(function success(data){
                $scope.linije = data.data;
                $scope.totalPages = data.headers('totalPages');

            });
    };

    var getPrevoznici = function(){

        $http.get(baseUrlPrevoznici)
            .then(function success(data){
                $scope.prevoznici = data.data;
            });

    };

    $scope.dodajPrevoznika = function(id){
        $location.path('/prevoznici/dodaj/' + id);
    }

    getLinije();
    getPrevoznici();
   
    $scope.go = function(direction){
        $scope.pageNum = $scope.pageNum + direction;
        getLinije();
    }

    $scope.dodaj = function(){
        $http.post(baseUrlLinije, $scope.novaLinija)
            .then(
            	function success(data){
	            	getLinije();
	
	            	$scope.novaLinija.brojMesta = "";
                    $scope.novaLinija.cenaKarte = "";
                    $scope.novaLinija.vremePolaska = "";
                    $scope.novaLinija.destinacija = "";
                    $scope.novaLinija.prevoznikId = "";
            	},
            	function error(data){
            		alert("Neuspesno dodavanje linije!");
            	}
            );
    };

    $scope.trazi = function () {
        $scope.pageNum = 0;
        getLinije();
    }

    $scope.izmeni = function(id){
        $location.path('/linije/edit/' + id);
    }

    $scope.obrisi = function(id){
        $http.delete(baseUrlLinije + "/" + id).then(
            function success(data){
            	getLinije();
            },
            function error(data){
                alert("Neuspesno brisanje!");
            }
        );
    }
    
    $scope.kupljenaKarta = {};
       
    $scope.kupi = function(id, brojMesta){

    $http.post(baseUrlLinije + "/" + id + "/kupovine", brojMesta).then(
    		function success(data){
                $scope.kupljenaKarta = data.data;
                alert("Karta je uspešno kupljena. Ukupna cena karte je " + $scope.kupljenaKarta.cena + " dinara.");
    			getLinije();
    		},
    		function error(data){
    			alert("Kupovina nije moguća.")
                
    		}
    	)
    }
});

stanicaApp.controller("editLinijaCtrl", function($scope, $http, $routeParams, $location){

    var baseUrlLinije = "/api/linije";

    $scope.staraLinija = null;

    var getStaraLinija = function(){

        $http.get(baseUrlLinije + "/" + $routeParams.id)
            .then(
            	function success(data){
            		$scope.staraLinija = data.data;
            	},
            	function error(data){
            		alert("Neušpesno dobavljanje linije.");
            	}
            );

    }
    getStaraLinija();
    
    $scope.izmeni = function(){
        $http.put(baseUrlLinije + "/" + $scope.staraLinija.id, $scope.staraLinija)
            .then(
        		function success(data){
        			alert("Uspešno izmenjena linija!");
        			$location.path("/");
        		},
        		function error(data){
        			alert("Neuspešna izmena linije.");
        		}
            );
    }
});

stanicaApp.controller('prevozniciCtrl', function($scope, $http, $location){
    
    var baseUrlPrevoznici = "/api/prevoznici";

    $scope.prevoznici = [];

    $scope.noviPrevoznik = {};
    $scope.noviPrevoznik.naziv = '';
    $scope.noviPrevoznik.adresa = '';
    $scope.noviPrevoznik.pib = '';

    $scope.dodaj = function(){
        $http.post(baseUrlPrevoznici, $scope.noviPrevoznik)
            .then(
                function success(data){
                   alert("Uspešno unet prevoznik.")

                   // $scope.noviPrevoznik.naziv = '';
                   // $scope.noviPrevoznik.adresa = '';
                   // $scope.noviPrevoznik.pib = '';
                   $location.path("/");

                },
                function error(data){
                    alert("Neuspesno dodavanje prevoznika!");
                }
            );
    };
})










