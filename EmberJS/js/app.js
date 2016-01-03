App = Ember.Application.create();

App.Router.map(function() {
  this.route("about");
  this.route("collections");
  this.resource("exhibits", function(){
    this.resource("exhibit", { path: "/:exhibit_id"});
  });
  this.route("notes");
});

/*
 * COLLECTIONS CODE STARTS HERE
 */

// Collections Route
App.CollectionsRoute = Ember.Route.extend({
  model: function() {
    return [
      {
        title: "Samoca Photography Collections",
        copy: "The Photography Gallery exhibits over 2,000 rotating examples from the permanent collection, which spans from the very beginnings of the medium to the present day. Collection highlights include silver  gelatin prints by Ansel Adams, landscapes by John Pfal, and portraits  by Robert Mapplethorpe.",
        image: "images/collections/collections-photography.jpg"
      }, {
        title: "Samoca Painting Collections",
        copy: "The SAMOCA collection of paintings includes work from 1950 to the present, and is ever expanding. The collection is especially renowned for its emphasis on California art and artists, including Sam Francis, Nathan Oliveira, and Wayne Thiebaud.",
        image: "images/collections/collections-painting.jpg"
      }, {
        title: "Samoca Sculpture Collections",
        copy: "Comprising American sculpture from the mid-19th through the late 20th centuries, SAMOCA's collection is famed for its almost complete series of Frederic Remington's, as well as the extensive outdoor sculpture gardens containing work from modernist Americans such as Richard Serra and Isamu Noguchi.",
        image: "images/collections/collections-sculpture.jpg"
      }
    ];
  }
});

// Customize the Collections component
App.SingleCollectionComponent = Ember.Component.extend({
  tagName: "article",
  classNames: ["collectionArticleClass cf"]
});

/*
 * CONTROLLERS CODE STARTS HERE
 */

// Route for all Exhibits
App.ExhibitsRoute = Ember.Route.extend({
  model: function() {
    return $.getJSON("js/exhibits.json").then(function(data) {
      return data.exhibits;
    });
  }
});

// Route for a single Exhibit
App.ExhibitRoute = Ember.Route.extend({
  model: function(params) {
    return $.getJSON("js/exhibits.json").then(function(data) {
      var modelId = params.exhibit_id - 1;
      data.exhibits.title = data.exhibits[modelId].title;
      data.exhibits.artist_name = data.exhibits[modelId].artist_name;
      data.exhibits.exhibit_info = data.exhibits[modelId].exhibit_info;
      data.exhibits.image = data.exhibits[modelId].image;
      return data.exhibits;
    });
  }
});

// Array controller...decorates all model data
App.ExhibitsController = Ember.ArrayController.extend({
  totalExhibits: function(){
    return this.get("model.length");
  }.property("@each")
});

// Object controller...decorates a single piece of model data
App.ExhibitController = Ember.ObjectController.extend({
  exhibitTitle: function(){
    return this.get("title") + " by " + this.get("artist_name");
  }.property("artist_name", "title")
});

