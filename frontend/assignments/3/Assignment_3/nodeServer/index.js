// Node server which will handle socket io connections

const io = require('socket.io')(8000, {
    cors: {
      origin: '*',
    }
  });

const users = {};

io.on('connection', socket =>{
    socket.on('new-user-joined', name =>{      // here the node Server accepts the event named new-user-joined
        console.log("New user", name);        // if new-user-joined event is found, it will add the user name to the users list
        users[socket.id] = name;                // provides unique id to the socket (the user)
        socket.broadcast.emit('user-joined', name);  //to emit event (that a new user has joined)to all other users except the new user who joined
    });

    socket.on('send', message =>{       //if event is - send, others will receive the message
        socket.broadcast.emit('receive', {message: message,name: users[socket.id]})
    });
    
    socket.on('disconnect', message =>{
        socket.broadcast.emit('left', users[socket.id]);   // to inform other users if a user leaves the chat and tell his name
        delete users[socket.id]; // to delete the user who left ,from our users list
    });
})

module.exports = {users};