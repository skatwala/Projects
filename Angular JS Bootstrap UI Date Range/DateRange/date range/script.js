angular.module('app', ['datepickerMultiSelect'])
.controller('AppCtrl', function() {
  this.activeDate = null;
  this.activeDate2 = null;
  
  this.selectedDates = [new Date().setHours(0, 0, 0, 0)];
  this.selectedDates2 = [new Date().setHours(0, 0, 0, 0)];
  
   
  this.type = 'range';
  
  this.show2pickers = false;
  
  this.removeFromSelected = function(dt) {
    this.selectedDates.splice(this.selectedDates.indexOf(dt), 1);
  }
  
  this.removeFromSelected2 = function(dt) {
    this.selectedDates2.splice(this.selectedDates2.indexOf(dt), 1);
  }
});