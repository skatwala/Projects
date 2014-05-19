/**
 * This View renders Collection of menu items
 * It listens to events for Collection content being added or deleted
 * It refreshes the contents of the collection
 * Saptarshi May 2014
 */
var MenuView = Backbone.View.extend({

	initialize: function  () {
		this.listenTo(this.collection, "reset", this.render);
		this.listenTo(this.collection, "add", this.render);
		this.listenTo(this.collection, "remove", this.render);
	},
	
	render: function () {
		this.$el.html(Handlebars.templates.menu(this.collection));
		return this;
	}

});