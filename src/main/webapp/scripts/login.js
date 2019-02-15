window.addEventListener('load', init);

function init(){
    document.querySelector("#log-btn").addEventListener('click', loginUser);
    let userData;
    // if(localStorage.getItem("userData")!=null){
    //     window.location.replace("./userpage.html");
    // }
}

function loginUser(event){
    event.preventDefault();
    
    fetch( 
            "http://localhost:9090/webservice/webapi/useroperations/login",
        {
            	"credentials": "omit",
                "headers": {
                    "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                    "accept-language": "en-GB,en-US;q=0.9,en;q=0.8",
                    "cache-control": "max-age=0",
                    "content-type": "application/x-www-form-urlencoded",
                    "upgrade-insecure-requests": "1"
                },
                "referrer": "http://localhost:9090/webservice/signup.html",
                "referrerPolicy": "no-referrer-when-downgrade",
                "body": new URLSearchParams(getRegFormData()).toString(),
                "method": "POST",
                "mode": "cors"
        }).then(response=>response.json()).catch(err=>alert("Please recheck your credentials and Try Again"))
    	  .then((data)=>{
    		  		if(data != undefined){
    		  			settingUpUser(data);  
    		  			}
    	  	});
      
}


function settingUpUser(userData){
	if (typeof(Storage) !== "undefined") {
			localStorage.setItem("userData", JSON.stringify(userData));
            alert("User Successfully logged in");
            window.location.replace("webservice/userpage.html")
		} else {
		  alert("Please allow to save  data in browser local database if you want to Log in");
		}
}


function getRegFormData(){
    let userData = new FormData();
    userData.append('email', document.querySelector("#log-email").value);
    userData.append('pass', document.querySelector("#log-pass").value);
    return userData;
}

