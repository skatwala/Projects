angular.module('app', ['datepickerMultiSelect'])
.controller('AppCtrl', function($scope,  $filter, $interval,$rootScope,mySharedService) {
  var vm = this;
  this.activeDate = null;
  this.showDateRangeControl=false;
  this.selectedDates = [new Date().setHours(0, 0, 0, 0)];

  this.type = 'range';
  this.dateValue="";

  $scope.$watch(vm.selectedDates.length, function () {
    //vm.returnDate();
  } );
  $scope.$watchGroup(vm.selectedDates , function () {
    //vm.returnDate();
  } );

  $scope.$watch('vm.selectedDates.length', function () {
    //vm.returnDate();
  } );
  /*$scope.$watchGroup('vm.selectedDates' , function () {
    vm.returnDate();
  } );*/

  $scope.$on('DateSelected', function(event, data) { console.log(data); vm.returnDate();});
  $scope.$on('handleBroadcast', function() {
    $scope.message = mySharedService.message;
    console.log(" $scope.message $scope.message    "+$scope.message);
    if ($scope.message === 'Show Calendar')
    {
    vm.showDateRangeControl=true;
    }
    else
    {
      console.log(" 1 $scope.message $scope.message    "+$scope.message);
      vm.showDateRangeControl=false;
    }
    $scope.$apply();
  });
  this.returnDate= function(){
    //vm.dateValue=   $filter('date')((vm.selectedDates )[0], "yyyy-MM-dd");
    vm.dateValue=   $filter('date')((vm.selectedDates )[0], "MM-dd-yy");
    if(vm.selectedDates.length > 1){
      vm.dateValue = $filter('date')((vm.selectedDates )[0], "MM-dd-yy") + " to "+$filter('date')((vm.selectedDates )[1], "MM-dd-yy");
      mySharedService.prepForBroadcast("Hide Calendar");
    }

    return vm.dateValue;
  };

/*$interval( function(){vm.returnDate()},10000);*/
/*$interval( function(){vm.returnDate()},10000);*/

  this.focus = function () {
    vm.showDateRangeControl=true;
  };


}).directive('selectOnClick', function (mySharedService) {
  return {
    restrict: 'A',
    link: function (scope, element) {
      var focusedElement;
      element.on('focus', function () {
        mySharedService.prepForBroadcast("Show Calendar");
        element.val('');
         /*if (focusedElement != this) {

          this.select();

          focusedElement = this;
        }*/
      });
      /*element.on('blur', function () {
        focusedElement = null;
      });*/
    }
  };
}).factory('mySharedService', function($rootScope) {
  var sharedService = {};

  sharedService.message = '';

  sharedService.prepForBroadcast = function(msg) {
    this.message = msg;
    this.broadcastItem();
  };

  sharedService.broadcastItem = function() {
    $rootScope.$broadcast('handleBroadcast');
  };

  return sharedService;
});