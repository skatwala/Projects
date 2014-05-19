/**
 * This View renders a single Menu Item
 * The associated pre compiled handlebars js template has a delete button
 * This View has event binding and defintion for the delete button
 * Saptarshi May 2014
 */
var MenuItemDetails = Backbone.View.extend({


	initialize: function  () {
		this.listenTo(this.model, "change", this.render);
	},

	deleteItem: function () {
		this.model.destroy(
			{
				success: function (model) {
					app.menuItems.remove(model.get('id'));
					app.navigate("", {trigger: true});
				}
			}
		);
	},
	
	render: function () {
		this.$el.html(Handlebars.templates.details(this.model.attributes));
		this.delegateEvents({
			'click .btn-danger': 'deleteItem'
		});
		return this;
	}
});