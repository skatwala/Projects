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
var timedisplay_pipe_1 = require("./timedisplay.pipe");
var ToolbarComponent = (function () {
    function ToolbarComponent(videoService) {
        this.videoService = videoService;
    }
    ToolbarComponent = __decorate([
        core_1.Component({
            selector: 'video-toolbar',
            template: "\n    <div id=\"playerToolBar\">\n        <a id=\"playBtn\" class=\"btn btn-default\" (click)=\"videoService.playVideo()\">\n        \t  <i [ngClass]=\"{'fa-play': !videoService.isPlaying, 'fa-pause': videoService.isPlaying}\" class=\"fa\"></i>\n        </a>\n        <a id=\"muteBtn\" class=\"btn btn-default\" (click)=\"videoService.muteVideo()\">\n            <i [ngClass]=\"{'fa-volume-off': videoService.isMuted, 'fa-volume-up': !videoService.isMuted}\" class=\"fa\"></i>\n        </a>\n        <span id=\"videoTime\">{{videoService.currentTime | timeDisplay}} / {{videoService.totalTime | timeDisplay}}</span>\n        <a id=\"fsBtn\" class=\"btn btn-default pull-right\" (click)=\"videoService.fullScreen()\">\n            <i class=\"fa fa-expand\"></i>\n        </a>\n        <a id=\"detailsBtn\" class=\"btn btn-default pull-right\" (click)=\"videoService.details()\">\n        \t   <i class=\"fa fa-bars\"></i>\n        </a>\n    </div>\n    ",
            pipes: [timedisplay_pipe_1.TimeDisplayPipe]
        }), 
        __metadata('design:paramtypes', [video_service_1.VideoService])
    ], ToolbarComponent);
    return ToolbarComponent;
}());
exports.ToolbarComponent = ToolbarComponent;
//# sourceMappingURL=toolbar.component.js.map