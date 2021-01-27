require 'rspec'
require 'guess_number.rb'

RSpec.describe 'guess_number_spec' do
  it 'should guess correct on first attempt' do
    selected_number = 10
    received = ''

    guess(selected_number, lambda { 10 }, lambda { |response| received = response })
    expect(received).to eq('You guessed it in 1 attempt')
  end

  it 'should guess smaller on first attempt' do
    selected_number = 10
    received = ''

    guess(selected_number, lambda { 9 }, lambda { |response| received = response })
    expect(received).to eq('Aim higher')
  end

  it 'should guess higher on first attempt' do
    selected_number = 10
    received = ''

    guess(selected_number, lambda { 11 }, lambda { |response| received = response })
    expect(received).to eq('Aim lower')
  end

  it 'should guess correct value the second attempt' do
    selected_number = 12
    attempts = 2
    received = ''

    guess(selected_number, lambda { 12 }, lambda { |response| received = response }, attempts)
    expect(received).to eq('You guessed it in 2 attempts')
  end

  it 'should return true for correct guess' do
    selected_number = 12
    attempts = 1

    expect(guess(selected_number, lambda { 12 }, lambda { |response| }, attempts)).to be true
  end

  it 'should return true on correct guess to any attempts' do
    selected_number = 15
    attempts = 3

    expect(guess(selected_number, lambda { 15 }, lambda { |response| }, attempts)).to be true
  end

  it 'should return false on incorrect guess' do
    selected_number = 15
    attempts = 4

    expect(guess(selected_number, lambda { 1 }, lambda { |response| }, attempts)).to be false
  end

  it 'should play correct value on first attempt' do
    selected_number = 20
    received = ''
    play(selected_number, lambda { 20 }, lambda {|response| received = response})
    expect(received).to eq('You guessed it in 1 attempt') 
  end
 
  it 'should play lower and then correct value on second attempt' do
    selected_number = 20
    guesses = [15, 20]    
    received = []
    
    play(selected_number, lambda { guesses.shift }, lambda { |response| received.push(response) })
    expect(received).to eq(['Aim higher', 'You guessed it in 2 attempts'])
  end

  it 'should play higher and then correct value on second attempt' do
    selected_number = 15
    guesses = [25, 15]
    received = []

    play(selected_number, lambda { guesses.shift}, lambda { |response| received.push (response) })
    expect(received).to eq(['Aim lower', 'You guessed it in 2 attempts'])
  end

  it 'should play higher, higher, and then correct value on the third attempt' do
    selected_number = 15
    guesses = [25, 20, 15]
    received = []

    play(selected_number, lambda { guesses.shift}, lambda { |response| received.push (response) })
    expect(received).to eq(['Aim lower', 'Aim lower', 'You guessed it in 3 attempts'])
  end

  it 'should lower, higher, and then correct value on the third attempt' do
    selected_number = 15
    guesses = [5, 20, 15]
    received = []

    play(selected_number, lambda { guesses.shift}, lambda { |response| received.push (response) })
    expect(received).to eq(['Aim higher', 'Aim lower', 'You guessed it in 3 attempts'])
  end
end
