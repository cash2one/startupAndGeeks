define(['knockout','text!./site-footer.html'], function(ko, template){

	function FooterViewModel(params) {
		this.route = params.route;
	}

	return {
		viewModel: FooterViewModel,
		template: template
	}
});
