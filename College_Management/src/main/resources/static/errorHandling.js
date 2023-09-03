function hideErrorMessages(){
	
	let message = document.querySelectorAll('.error-message');
	
	message.forEach(function(errormessage){
		setTimeout(function(){
			errormessage.style.display = "none";
		}, 3000);
	});
}

document.addEventListener("DOMContentLoaded", function(){
	hideErrorMessages();
})