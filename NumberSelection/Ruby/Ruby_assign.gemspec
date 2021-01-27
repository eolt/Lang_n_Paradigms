require_relative 'lib/Ruby_assign/version'

Gem::Specification.new do |spec|
  spec.name          = "Ruby_assign"
  spec.version       = Ruby_assign::VERSION
  spec.authors       = [""]
  spec.email         = [""]

  spec.summary       = %q{This is a prgram}
  spec.license       = "MIT"
  spec.required_ruby_version = Gem::Requirement.new(">= 2.3.0")

  spec.bindir        = "exe"
  spec.executables   = spec.files.grep(%r{^exe/}) { |f| File.basename(f) }
  spec.require_paths = ["lib", "spec"]
end
