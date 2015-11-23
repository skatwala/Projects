 
(function (angular) {
	'use strict';
	
	angular.module('datepickerMultiSelect', ['ui.bootstrap'])
	.config(['$provide', '$injector',   function ($provide, $injector ) {
		// extending datepicker (access to attributes and app scope through $parent)
		var datepickerDelegate = function ($delegate,$rootScope) {
			var directive = $delegate[0];
			console.log("DIRECTIVE = "+JSON.stringify(directive));
			// Override compile
			var link = directive.link;
			directive.compile = function () {
				return function (scope, element, attrs, ctrls) {
					link.apply(this, arguments);
					scope.selectedDates = [];

					scope.$parent.$watchCollection(attrs.multiSelect, function (newVal) {
						scope.selectedDates = newVal || [];
					});

					var ngModelCtrl = ctrls[1];
					ngModelCtrl.$viewChangeListeners.push(function() {
						var newVal = scope.$parent.$eval(attrs.ngModel);
						if(!newVal)
							return;
						var dateVal = newVal.getTime(),
							selectedDates = scope.selectedDates;

							// reset range
							if (!selectedDates.length || selectedDates.length > 1 || selectedDates[0] == dateVal)
								return selectedDates.splice(0, selectedDates.length, dateVal);
							selectedDates.push(dateVal);
							var tempVal = Math.min.apply(null, selectedDates);
							var maxVal = Math.max.apply(null, selectedDates);
							// Start on the next day to prevent duplicating the	first date
							tempVal = new Date(tempVal).setHours(24);
							while (tempVal < maxVal) {
								selectedDates.push(tempVal);
								scope.$emit('DateSelected',{});
								// Set a day ahead after pushing to prevent duplicating last date
								tempVal = new Date(tempVal).setHours(24);
							}
					});
				}
			};
			return $delegate;
		};

		if ($injector.has('uibDatepickerDirective'))
			$provide.decorator('uibDatepickerDirective', ['$delegate', datepickerDelegate]);
		// extending daypicker (access to day and datepicker scope through $parent)
		var daypickerDelegate = function ($delegate) {
			var directive = $delegate[0];
			// Override compile
			var link = directive.link;
			directive.compile = function () {
				return function (scope, element, attrs, ctrls) {
					link.apply(this, arguments);
					scope.$parent.$watchCollection('selectedDates', update);
					/*
						Fires when date is selected or when month is changed.
						UI bootstrap versions before 0.14.0 had just one controller DatepickerController,
						now they have UibDatepickerController, UibDaypickerController and DatepickerController
						see more on https://github.com/angular-ui/bootstrap/commit/44354f67e55c571df28b09e26a314a845a3b7397?diff=split#diff-6240fc17e068eaeef7095937a1d63eaeL251
						and https://github.com/angular-ui/bootstrap/commit/44354f67e55c571df28b09e26a314a845a3b7397?diff=split#diff-6240fc17e068eaeef7095937a1d63eaeR462
					*/
					var ctrl = angular.isArray(ctrls) ? ctrls[0] : ctrls;
					scope.$watch(function () {
						return ctrl.activeDate.getTime();
					}, update);
					function update() {
						angular.forEach(scope.rows, function (row) {
							angular.forEach(row, function (day) {
								day.selected = scope.selectedDates.indexOf(day.date.setHours(0, 0, 0, 0)) > -1
							});
						});
					}
				}
			};
			return $delegate;
		};

		if ($injector.has('uibDaypickerDirective'))
			$provide.decorator('uibDaypickerDirective', ['$delegate', daypickerDelegate]);
	}]);
})(window.angular);