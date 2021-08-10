const socket = io('http://localhost:8000');     // it is working on port:8000

const form = document.getElementById('send-container');
const messageInput = document.getElementById('messageInp');
const messageContainer = document.querySelector(".container");   

var audio = new Audio('trinsound.mp3');

//function to append messages to the message container
const append = (message, position)=>{    //position is the left or right position of the message in the container 
    const messageElement = document.createElement('div');
    messageElement.innerText = message;
    messageElement.classList.add('message');
    messageElement.classList.add(position);
    messageContainer.append(messageElement);  //we add the new messages to the message container
    if(position == 'left')   // so that we listen to the notification sound only when we receive a message and not when we send a message
    {
        audio.play();  // play the notification sound   
        messageElement.style.backgroundColor = "rgb(132, 68, 161)";
        
    }

}

form.addEventListener('submit', (e)=>{           // if the form is submitted a function runs that emits the send event
    e.preventDefault();
    const message = messageInput.value ;
    append(`You: ${message}`, 'right');
    socket.emit('send', message);  // tells the node server that we are sending a message
    messageInput.value = ''   // to make the input area blank after sending the message
})

const name  = prompt("Enter your name to join");
socket.emit('new-user-joined', name); // here we emit an event named new-user-joined

socket.on('user-joined', name =>{       // here we listen to the event user-joined which was emitted and
    append(`${name} joined the chat`, 'right')  // run a function which appends in the message container the name of user who joined the chat
})

socket.on('receive', data =>{         // whenever we listen to a receive event , we append that message in 
    append(`${data.name}: ${data.message}`, 'left')
})

socket.on('left', name =>{
    append(`${name} left the chat`, 'left')
})