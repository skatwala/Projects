/**
 * This Collection is based off the MenuItem model
 * The MenuItem model uses the "intems" REST Service
 * This collection uses Comaparator to sort the list returned by the Service
 * Saptarshi May 2014
 */

var MenuItems = Backbone.Collection.extend({
	comparator: 'name',
	model: MenuItem,
	url: '/items'
});