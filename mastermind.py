import random
import os

CodeToGuess = []
slots = 4
Colors = [1,2,3,4,5,6]

Initial_guess = [1,1,2,3]

population_size = 100
generations = 60

def play_guess(code, guess):
	#plays the guess getting a score from it
	black_pins = 0
	white_pins = 0
	code_copy = []
	for cd in code:
		code_copy.append(cd)

	guess_copy = []
	for cd in guess:
		guess_copy.append(cd)

	for i in range(len(guess_copy)):
		if guess_copy[i] == code_copy[i]:
			black_pins = black_pins + 1
			code_copy[i]= -1
			guess_copy[i] = -1
	for color in guess_copy:
		if color != -1 and color in code_copy:
			white_pins = white_pins + 1
			i = code_copy.index(color)
			code_copy[i] = -1
	return (black_pins, white_pins)


def fitness(guess, trial):
#Assigns a fitness value to a posible guess, based on the previous guesses score
	guess_result = guess[1]
	guess_code = guess[0]
	trial_result = play_guess(guess_code, trial)

	black_pins_diff = abs(trial_result[0] - guess_result[0])
	white_pins_diff = abs(trial_result[1] - guess_result[1])

	fitness_score = black_pins_diff + white_pins_diff
	return fitness_score


def crossover(code1, code2):
	newcode = []
	for i in range(slots):
		if random.random() > 0.5:
			newcode.append(code1[i])
		else:
			newcode.append(code2[i])
	return newcode

def mutate(code):
	i = random.randint(0, slots-1)
	v= random.randint(1, len(Colors))
	code[i] = v
	return code

def permute(code):
	for i in range(slots):
		if random.random() <= 0.03:
			random_color_position_a = random.randint(0, slots-1)
			random_color_position_b = random.randint(0, slots-1)

			color_a = code[random_color_position_a]

			code[random_color_position_a] = code[random_color_position_b]
			code[random_color_position_b] = color_a

	return code



def main(): 
	CodeToGuess = [1,2,3,4]
	tmp = []
	if (len(CodeToGuess) != 4):
		print("The code must have 4 numbers")
		exit()
	for number in CodeToGuess:
		if int(number) > 6	:
			print("The numbers representing colors should be between 1 and 6")
			exit()
	
	random.seed(os.urandom(32))

	guesses = []

	result = play_guess(CodeToGuess, Initial_guess)
	guesses.append((Initial_guess, result))
	print"The guess %s gave the result %d black pins and %d white pins" % (Initial_guess, result[0], result[1])
	turn = 1
	while result != (4,0):
		#initial population
		population = [[random.randint(1, len(Colors)) for i in range(slots)]\
					for j in range(population_size)]
		chosen_ones = []
		h = 1
		k = 0
		while len(chosen_ones) <= population_size and h <= generations:

			sons = []
			for i in range(len(population)):

				if i == len(population) -1:
					sons.append(population[i])
					break
				son = crossover(population[i], population[i+1])

				if random.random() <= 0.03:
					son = mutate(son)

				son = permute(son)

				sons.append(son)

			pop_score = []
			for c in sons:
				score = 0
				for guess in guesses:
					score = score + fitness(guess, c)
				pop_score.append((score, c))

			#We assign a fitness score to the guesses and order them by that score
			pop_score = sorted(pop_score, key=lambda x: x[0])

			eligibles = [(score, e) for (score, e) in pop_score if score == 0]
			if len(eligibles) == 0:
				h = h + 1
				continue

			#We only need the code from the eligible codes
			new_eligibles = []
			for (score, c) in eligibles:
				new_eligibles.append(c)
			eligibles = new_eligibles

			#We add the eligibles code to the chosen_ones if is not alredy there, and we check if we 
			#break the max population limit

			for eligible in eligibles:
				if len(chosen_ones) == population_size:
					break

				if not eligible in chosen_ones and not eligible in guesses:
					chosen_ones.append(eligible)

			
			#We create a new population for the next iteration based on the eligible codes
			#if we arent able to fill population limit we create random codes
			population = []
			population.extend(eligibles)

			j = len(eligibles)
			while j < population_size:
				population.append([random.randint(1, len(Colors)) for i in range (slots)])
				j = j + 1
			h = h + 1
		turn = turn + 1
		code_to_play = chosen_ones.pop()
		result = play_guess(CodeToGuess, code_to_play)
		print"The guess %s gave the result %d black pins and %d white pins" % (code_to_play, result[0], result[1])
		if result == (4,0):
			print "The AI won in %d turns!" % (turn)
			exit()
	

if __name__ == '__main__':
	main()