/**
 * 
 */
	$(function(){
		var gnb = $("#gnb").offset().top;
		$(window).scroll(function(){
			var window = $(this).scrollTop();
			if(gnb <= window){
				$("#gnb").addClass("fixed");
			}else{
				$("#gnb").removeClass("fixed");
			}
		});
	});