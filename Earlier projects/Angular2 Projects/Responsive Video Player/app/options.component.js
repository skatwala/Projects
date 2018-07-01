"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var video_service_1 = require("./video.service");
var OptionsComponent = (function () {
    function OptionsComponent(videoService) {
        this.videoService = videoService;
    }
    OptionsComponent = __decorate([
        core_1.Component({
            selector: 'video-options',
            template: "\n    <div id=\"VideoOptions\" [hidden]=\"!videoService.showDetails\" class=\"card\">\n        <div class=\"panel-heading\">\n            <h1 class=\"panel-title\">{{videoService.currentTitle}}</h1>\n        </div>\n        <div class=\"panel-body\">\n            <div class=\"col-xs-6\">\n                <h2>Video Details:</h2>\n                <p>{{videoService.currentDesc}}</p>\n            </div>\n            <div class=\"col-xs-6\">\n                <ul class=\"list-group\">\n                    <h2>Playlist:</h2>\n                    <li class=\"list-group-item\" *ngFor=\"let playlistItem of videoService.playlist; let i = index\" (click)=\"videoService.selectedVideo(i)\">{{playlistItem.title}}</li>\n                </ul>\n            </div>\n        </div>\n    </div>\n    "
        }), 
        __metadata('design:paramtypes', [video_service_1.VideoService])
    ], OptionsComponent);
    return OptionsComponent;
}());
exports.OptionsComponent = OptionsComponent;
//# sourceMappingURL=options.component.js.map