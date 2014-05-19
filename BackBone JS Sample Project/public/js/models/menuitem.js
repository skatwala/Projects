/**
 * This is the MenuItem Model
 * it gets its data from the "/items" Node JS Rest services
 * Saptarshi May 2014
 */

var MenuItem = Backbone.Model.extend({
	urlRoot: '/items',
	defaults: {
		category: 'Entreés',
		imagepath: 'no-image.jpg',
		name: ''
	}
});