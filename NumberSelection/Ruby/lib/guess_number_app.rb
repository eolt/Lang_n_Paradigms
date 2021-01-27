require_relative 'guess_number'

puts "I've selected a number, can you guess it"

play(rand(101), lambda { print 'Your guess: ';  $stdin.gets.chomp.to_i }, method(:puts))

