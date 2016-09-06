# Sinatra HTTP Testing Server
# Place file as Server.rb and run with `ruby Server.rb` before executing the test suite
# Service will be available at base URL http://localhost:4567

require 'rubygems'
require 'sinatra'
require 'json'

# See http://www.sinatrarb.com/configuration.html for configuring settings
configure do
    set :logging, true
    set :dump_errors, true
    
    # Set public folder as the root of Server.rb
    set :public_folder, Proc.new { File.expand_path(root) }
end

before do
    # Disable cache to not mess up tests
    cache_control :no_cache, :max_age => 0
end

def render_fixture(filename)
    send_file File.join(settings.public_folder, filename)
end

# For routes, see http://www.sinatrarb.com/intro.html

# Creates a route
get '/success' do
    content_type 'application/json'
    '[{"id":12345, "name":"cassiano"}]'
end

# Creates a route
get '/error' do
    halt 400
end