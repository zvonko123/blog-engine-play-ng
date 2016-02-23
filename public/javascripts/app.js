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

                $scope.showAddComment = [];

                for (i=0;i<$scope.allPostsAndComments.length;i++)
                {
                    $scope.showAddComment[i] = false;
                }

            });


        };

        $scope.freshData();


        $scope.showTopic = true;



        //we use the topic id
        $scope.addComment = function(id){
            if ($scope.showAddComment[id])
            {
                $scope.showAddComment[id] = false;
            }
            else{
                $scope.showAddComment[id] = true;
            }
        };

    });