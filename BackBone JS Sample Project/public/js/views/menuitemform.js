/**
 * This View if for the FORM to create a new Menu Item
 * The associated pre compiled handlebars js template is a FORM
 * that has a Save button.  
 * This View has logic to read the form content and event binding and defintion for the Save button
 * Saptarshi May 2014
 */
var MenuItemForm = Backbone.View.extend({


	render: function  () {
		this.$el.html(Handlebars.templates.form());
		this.delegateEvents({
			'click .btn-primary': 'save'
		});
		return this;
	},

	save: function () {
		this.setModelData();

		this.model.save(this.model.attributes, 
			{
				success: function (model) {
					app.menuItems.add(model);
					app.navigate('menu-items/' + model.get('url'), {trigger: true});
				}
			}
		);
	},

	setModelData: function  () {
		this.model.set({
			name: this.$el.find('input[name="name"]').val(),
			category: this.$el.find('input[name="category"]').val(),
			id: null,
			url: this.$el.find('input[name="url"]').val(),
			imagepath: this.$el.find('input[name="imagepath"]').val()
		});
	}

});