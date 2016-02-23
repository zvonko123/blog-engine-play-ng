var demoApp = angular.module('blogApp',[]);

angular.module('blogApp').controller('blogCtrl',
    function ($scope,$http,$filter) {

        //fetch fresh data from server
        $scope.freshData = function () {
            console.log("trying to fetch fresh posts and comments");
            $http({
                method: 'POST',
                url: 'http://localhost:9000/getFreshData',
                data: JSON.stringify("null")
            }).then(function (response) {

                $scope.allPostsAndComments = response.data;
                console.log("fetched",response.data);
            });


        };

        $scope.freshData();

    });