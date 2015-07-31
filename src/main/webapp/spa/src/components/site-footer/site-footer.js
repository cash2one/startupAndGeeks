define(['knockout','text!./baiye-footer.html'], function(ko, template){

	function FooterViewModel(params) {
		this.route = params.route;
	}

	return {
		viewModel: FooterViewModel,
		template: template
	}
});