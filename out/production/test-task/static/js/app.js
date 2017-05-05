var app = angular.module('app', []);
var editPropName = "editable";

var extractedUserUpdate = function (user) {
	var userUpdate = {id: "", phone: "", type: "", comment: ""};
	userUpdate.id = user.phoneId;
	userUpdate.phone = user.phone;
	userUpdate.type = user.type.toUpperCase();
	userUpdate.comment = user.comment;
	return userUpdate;
};

var deleteValueFromArray = function (array, val) {
	for (var i = 0; i < array.length; i++) {
		if(array[i] === val) {
			array.splice(i, 1);
			return array;
		}
	}
};

app.controller('appController', function ($scope, $http) {
	$scope.editIndexes = [];
	
	$scope.loadData = function () {
		$http.get('http://localhost:8080/api/v1/user')
			 .then(function (success) {
				 $scope.users = success.data;
			 }, function (error) {
				 console.log(error.msg);
				 $scope.users = [];
			 });
	};
	
	$scope.editData = function (index) {
		$http.get('http://localhost:8080/api/v1/user/type')
			 .then(function (success) {
				 $scope.types = success.data;
			 }, function (error) {
				 console.log(error.msg);
				 $scope.types = [];
			 });
		$scope.editIndexes.push(index);
		$scope.users[index][editPropName] = true;
	};
	
	$scope.saveData = function (index) {
		var userUpdate = [];
		userUpdate.push(extractedUserUpdate($scope.users[index]));
		$http.put("http://localhost:8080/api/v1/user/", userUpdate)
			.then(function (success) {
				$scope.users[index] = success.data[0];
				$scope.users[index][editPropName] = undefined;
				$scope.editIndexes = deleteValueFromArray($scope.editIndexes, index);
			}, function (error) {
				console.log(error.msg);
			});
	};
	
	$scope.saveAllData = function () {
		var userUpdate = [];
		for (var i = 0; i < $scope.editIndexes.length; i++) {
			var user = $scope.users[$scope.editIndexes[i]];
			userUpdate[i] = extractedUserUpdate(user);
		}
		$http.put("http://localhost:8080/api/v1/user/", userUpdate)
			 .then(function (success) {
			 	 var data = success.data;
				 for (var i = 0; i < $scope.editIndexes.length; i++) {
					 var index = $scope.editIndexes[i];
					 $scope.users[index] = data[i];
				 	 $scope.users[index][editPropName] = undefined;
				 }
				 $scope.editIndexes = [];
			 }, function (error) {
				 console.log(error.msg);
			 });
	};
	
	$scope.loadData();
});