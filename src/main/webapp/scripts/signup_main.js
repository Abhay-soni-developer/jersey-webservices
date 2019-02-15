window.addEventListener('load', init);

function init(){
    // console.log("HELlo world")
    document.querySelector("#sub_btn").addEventListener('click', registerUser);
}

function registerUser(event){
//	action="/webservice/webapi/useroperations/reg" method='post'
    event.preventDefault(); 
    fetch( 
        "http://localhost:9090/webservice/webapi/useroperations/reg",
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
    }    
    
    ).then(response=>response.json())
         .catch(err=>alert("Server Error : "+err)).then(data=>console.log(data));
  
}


function getRegFormData(){
    let userData = new FormData();
    userData.append('fname', document.querySelector("#sign-up-fname").value);
    userData.append('lname', document.querySelector("#sign-up-lname").value);
    userData.append('mobile', document.querySelector("#sign-up-mob").value);
    userData.append('age', 19);
    userData.append('gender', document.querySelector("#sign-up-gender input[type='radio']:checked").value);
    userData.append('email', document.querySelector("#sign-up-email").value);
    userData.append('country', document.querySelector("#sign-up-country").value);
    userData.append('pass', document.querySelector("#sign-up-pass").value);
    userData.append('repass', document.querySelector("#sign-up-repass").value);
    return userData;
}