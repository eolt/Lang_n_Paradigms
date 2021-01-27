def guess(selected_number, input, output, attempts = 1)
  responses = ['Aim lower', "You guessed it in #{attempts} " + (attempts == 1 ? 'attempt' : 'attempts'), 'Aim higher']
  guess_match = selected_number <=> input.call

  output.call(responses[guess_match + 1])
  
  guess_match.zero?
end

def play(selected_number, input, output)
  (1..).find { |attempts| guess(selected_number, input, output, attempts) } 
end
