import zmq

context = zmq.Context()

socket = context.socket(zmq.REP)
socket.connect("tcp://localhost:5555")

print("Waiting for message")
message = socket.recv()
print("[GOT] ", message)

socket.send(b"Hello from Python")
