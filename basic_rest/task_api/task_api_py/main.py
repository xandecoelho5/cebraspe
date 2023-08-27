from http.server import BaseHTTPRequestHandler, HTTPServer
import json


class handler(BaseHTTPRequestHandler):
  def do_GET(self):
    self.send_response(200)
    self.send_header('Content-type', 'application/json')
    self.end_headers()

    message = {'message': 'Hello, World!'}
    json_message = json.dumps(message)  # Convert dictionary to JSON string
    self.wfile.write(bytes(json_message, "utf8"))

  def do_POST(self):
    content_length = int(self.headers['Content-Length'])
    post_data = self.rfile.read(content_length)
    data = json.loads(post_data.decode('utf-8'))

    response_data = {'received_data': data}
    self.send_response(200)
    self.send_header('Content-type', 'application/json')
    self.end_headers()

    json_response = json.dumps(response_data)
    self.wfile.write(bytes(json_response, 'utf-8'))


with HTTPServer(('', 8000), handler) as server:
  server.serve_forever()
