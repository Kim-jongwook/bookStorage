/**
 * 
 */
	$(function(){
		$(".defaultOption").click(function(){
			if($(".dropdown ul").hasClass("active")){
				$(".dropdown ul").removeClass("active");
			}else{
				$(".dropdown ul").addClass("active");
			}
		});
		
		$(".dropdown ul li").click(function(){
			var text = $(this).text();
			$(".defaultOption").text(text);
			$(".dropdown ul").removeClass("active");
		});
		
		$(".dropdown ul").mouseleave(function(){
			$(".dropdown ul").removeClass("active");
		});
	});