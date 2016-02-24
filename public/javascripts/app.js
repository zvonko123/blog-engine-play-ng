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


        $scope.showTopic = true;



        //we use the topic id
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
            $scope.showTopic = false;
            $scope.topicToEdit = $scope.allPostsAndComments[index];
            $scope.showEditTopicArea = true;
        };

        $scope.addNewPost = function(){

        };

        $scope.submitEditPost = function(){
            //add POST request for DB update
            $scope.showTopic = true;
            $scope.showEditTopicArea = false;

            console.log("trying to submit edited post..",$scope.topicToEdit.title);
            $http({
                method: 'POST',
                url: 'http://localhost:9000/addPost',
                data: JSON.stringify({post_id : $scope.topicToEdit.id,body :$scope.topicToEdit.body,title: $scope.topicToEdit.title})
            }).then(function (response) {
                console.log("edit success",response.data);
                $scope.freshData();
            });

        }

    });