/**
 * AppRouter defines the Routes
 * it initializes the Models and the Collections
 * gets the Models from the Collections for re-use wherever appropriate
 * passes Models and Collections to Views
 * Uses jQuery to start BackBone after page is loaded
 */

var AppRouter = Backbone.Router.extend({
	routes: {
		"": "list",
		"menu-items/new": "itemForm",
		"menu-items/:item": "itemDetails"
	},

	initialize: function  () {
		this.menuItems = new MenuItems();
		this.menuItems.fetch();

		this.menuItemModel = new MenuItem();
		this.menuItemView = new MenuItemDetails(
			{
				model: this.menuItemModel
			}
		);
		
		this.menuView = new MenuView({collection: this.menuItems});
		this.menuItemForm = new MenuItemForm({model: new MenuItem()});
	},

	list: function () {
		$('#app').html(this.menuView.render().el);
	},

	itemDetails: function (item) {
		this.menuItemView.model = this.menuItems.get(item);
		$('#app').html(this.menuItemView.render().el);
	},

	itemForm: function () {
		$('#app').html(this.menuItemForm.render().el);
	}
});

var app = new AppRouter();

$(function() {
	Backbone.history.start();
});