(function($) {
	  $.fn.tagName = function() {
		  return this.get(0).tagName.toLowerCase();
	  }
})(jQuery);