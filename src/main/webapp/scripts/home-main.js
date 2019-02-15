window.addEventListener('load', init);

function init(){
    userData=JSON.parse(localStorage.getItem("userData"));
    document.querySelector('#cmt-post-btn').addEventListener('click', postComments);
    document.querySelector('#my-comments-btn').addEventListener('click', getMyComments);
    document.querySelector('#all-comments-btn').addEventListener('click', getLattestComments);
    // if(userData=(localStorage.getItem("userData")!=null)){
    //     alert("Sorry you need to login to enter this page");
    //     window.location.replace("./index.html");
    // }else{
    //     setSideUserBar(userData);
    // }
    setSideUserBar(userData);
}


function setSideUserBar(userData){
    document.querySelector("#user-fullname").innerHTML = userData.fname + " " + userData.lname;
    document.querySelector("#user-email").innerHTML = userData.email;
    document.querySelector("#user-mobile").innerHTML = userData.mobile;
    document.querySelector("#user-gender").innerHTML = userData.gender;
    document.querySelector("#user-country").innerHTML = userData.country;
}

function newCommentData() {document.querySelector('#new-comment').value
    let cmtData = new FormData();
    cmtData.append('userID', userData.id);
    cmtData.append('newComment', document.querySelector('#new-comment').value);
    return new URLSearchParams(cmtData).toString();
}

function postComments(){
    fetch( 
        "http://localhost:9090/webservice/webapi/commentoperations/addcomment?"+newCommentData(),
    {
            "credentials": "omit",
            "headers": {
                "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                "accept-language": "en-GB,en-US;q=0.9,en;q=0.8",
                "cache-control": "max-age=0",
                "content-type": "application/x-www-form-urlencoded",
                "upgrade-insecure-requests": "1"
            },
            "referrerPolicy": "no-referrer-when-downgrade",
            "method": "GET",
            "mode": "cors"
    }).then(response=>response.json()).catch(err=>alert("Sorry Your Comment can not be posted right now"))
      .then((data)=>{
                  if(data != undefined){
                      alert('Comment posted');
                      document.querySelector('#new-comment').value="";
                      }
          });
}

function appendCommentInCommentContainer(comment){
    let new_cmt_cont = createCommentMessageContainer();
    document.querySelector(".comments-container").appendChild(fillDataInCommentContainer(new_cmt_cont, comment));
}

function fillDataInCommentContainer(com_cont, comment){
    com_cont.querySelector(".comment-by").innerText = comment.userID;
    com_cont.querySelector(".comment-date").innerText = comment.date;
    com_cont.querySelector(".comment-msg").innerText = comment.comment;
    return com_cont;
}

function getMyComments(){
    fetchComments(10, 'getusercomments', userData.id);
}

function getLattestComments(){
    fetchComments(10, 'getcomments');
}

function fetchComments(limit, type, userID){

    let baseUrl = "http://localhost:9090/webservice/webapi/commentoperations/"
    baseUrl = baseUrl + type + '?';
    let formData = new FormData();
    if(userID==undefined){
        formData.append('limit', limit);
    }else{
        formData.append('userID', userData.id);
        formData.append('limit', limit);
    }

    fetch( baseUrl+new URLSearchParams(formData).toString()
        ,{"credentials": "omit",
                "headers": 
                    {"accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                     "accept-language": "en-GB,en-US;q=0.9,en;q=0.8",
                     "cache-control": "max-age=0",
                     "content-type": "application/x-www-form-urlencoded",
                     "upgrade-insecure-requests": "1"
                    },
            "referrerPolicy": "no-referrer-when-downgrade",
            "method": "GET",
            "mode": "cors"
    }).then(response=>response.json()).catch(err=>alert("Sorry Your Comment can not be retrieved right now"))
      .then((comments)=>{
                  if(comments != undefined){
                       document.querySelector('.comments-container').innerHTML = "";
                       comments.forEach(comment => {
                           appendCommentInCommentContainer(comment);
                       });
                    }
          });
}


function createCommentMessageContainer(){
    let cont = createCustomElement('div', 'comment');
    let com_detail = createCustomElement('div', 'comment-detail');
    let cmt_by = createCustomElement('span', 'comment-by');
    let cmt_date = createCustomElement('span', 'comment-date');
    cont.appendChild(com_detail);
    com_detail.appendChild(cmt_by);
    com_detail.appendChild(cmt_date);
    let com_msg_cont = createCustomElement('div', 'comment-msg-container');
    let cmt_msg = createCustomElement('span', 'comment-msg');
    com_msg_cont.appendChild(cmt_msg);
    cont.appendChild(com_msg_cont);
    return cont;
}



// creates a element with predefined class
function createCustomElement(e, c){
    let ele = document.createElement(e);
    ele.classList.add(c);
    return ele;
}


function logoutUser(){
    localStorage.removeItem('userData');
    alert('You are successfully logged out');
}