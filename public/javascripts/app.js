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

        $scope.addComment = function (post_id) {
            console.log("trying to post comment from ",$scope.commentName);
            $http({
                method: 'POST',
                url: 'http://localhost:9000/addComment',
                data: JSON.stringify({post_id : post_id.toString(),name :$scope.commentName,message : $scope.commentMessage})
            }).then(function (response) {
                console.log("comment added",response.data);

                $scope.showAddComment = [];

                for (i=0;i<$scope.allPostsAndComments.length;i++)
                {
                    $scope.showAddComment[i] = false;
                }

                $scope.freshData();
            });
        };

        $scope.freshData();


        $scope.showPost = true;



        //we use the post id
        $scope.addCommentArea = function(post_id){
            if ($scope.showAddComment[post_id])
            {
                $scope.showAddComment[post_id] = false;
            }
            else{
                $scope.showAddComment[post_id] = true;
            }
        };

        $scope.submitComment = function(post_id){
            $scope.addComment(post_id);
            console.log("name and message",$scope.commentName,$scope.commentMessage);
        };


        $scope.editPost = function(index){
            $scope.showPost = false;
            $scope.postToEdit = $scope.allPostsAndComments[index];
            $scope.showEditPostArea = true;
        };

        $scope.addNewPost = function(){

        };

        $scope.submitEditPost = function(){
            //add POST request for DB update
            $scope.showPost = true;
            $scope.showEditPostArea = false;

            console.log("trying to submit edited post..",$scope.postToEdit.title);
            $http({
                method: 'POST',
                url: 'http://localhost:9000/addPost',
                data: JSON.stringify({post_id : $scope.postToEdit.id,body :$scope.postToEdit.body,title: $scope.postToEdit.title})
            }).then(function (response) {
                console.log("edit success",response.data);
                $scope.freshData();
            });

        }

    });