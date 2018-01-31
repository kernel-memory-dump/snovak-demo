(function() {
    'use strict';

    angular
        .module('snovakApp')
        .controller('TopicDetailController', TopicDetailController);

    TopicDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Topic', 'User'];

    function TopicDetailController($scope, $rootScope, $stateParams, previousState, entity, Topic, User) {
        var vm = this;

        vm.topic = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('snovakApp:topicUpdate', function(event, result) {
            vm.topic = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
