/* Created Module */
var tipCalcControllers = angular.module('tipCalcControllers', []);

/* Tip Calc Application Controller */
tipCalcControllers.controller('tipCalcController', function($scope) {

    /* Declare Output Values and Locate Form */
    $scope.formElement = $("#tip-calc-form");
    $scope.tipOutput = "0";
    $scope.totalOutput = "0";

    /* Tip Amount Function
    *
    * @param float billAmount - parsed value from user-input for the bill amount.
    * @param float tipPercent - parsed value from user-input for the tip percent.
    *
    * @return float value - the product of billAmount and tipPercentage.
    */
    $scope.tipAmount = function(billAmount, tipPercent) {
        var value = billAmount * tipPercent;
        value = Math.round(value*100)/100;
        return value;
    };

    /* Total Amount Function
    *
    * @param float billAmount - parsed value from user-input for the bill amount.
    * @param float tipPercent - parsed value from user-input for the tip percent.
    *
    * @return value - the sum of the billAmount tipPercent.
    */
    $scope.totalAmount = function(billAmount, tipPercent) {
        var value = $scope.tipAmount(billAmount, tipPercent) + billAmount;
        value = Math.round(value*100)/100;
        return value;

    };

    /* formElement Submit Function
    *
    * Stores tip amount and bill total in $scope.tipOutput and $scope.totalOutput variables to be called in the template.
    *
    * @param float billAmount - parsed value from user-input for the bill amount.
    * @param float tipPercent - parsed value from user-input for the tip percent.
    *
    * @return void
    */
    $scope.formElement.submit(
        function(event) {
            event.preventDefault();
            var billInputValue = parseFloat($("#input-bill").val());
            var tipInputValue = parseFloat($("#input-tip").val());
            $scope.tipOutput = $scope.tipAmount(billInputValue, tipInputValue);
            $scope.totalOutput = $scope.totalAmount(billInputValue, tipInputValue);
        } // function();
    ); // $scope.formElement submit();
}); // tipCalcControllers controller();
