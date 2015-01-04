from sentiment import *
from operator import itemgetter


def get_stop_words(filename):
	words = open(filename, 'r').readlines()
	stopwords = []
	for stopword in words:
		stopwords.append(stopword.replace('\n', ''))
	return stopwords


#returns a dict of {word : sentiment}
def infer_word_sentiments(codes, text):
	
	inferred_sentiments = {}
	stopwords = get_stop_words("data/stopwords.txt")
	word_count = {}
	
	for t in text:
		words = t.split(' ')
		for w in words:
			if "http" in w or '@' in w or '#' in w or w == '':
				break
				
			if ((w not in stopwords) and (not codes.has_key(w))):
				if not inferred_sentiments.has_key(w):
					inferred_sentiments[w] = sentiment_score(codes, t)
					word_count[w] = 1
				else:
					inferred_sentiments[w] += sentiment_score(codes, t)
					inferred_sentiments[w] /= 2
					word_count[w] += 1
	
		
	return inferred_sentiments, word_count




def infer_tweet_word_sentiments(codes, tweets):
	tweetText = []
	for t in tweets:
		tweetText.append(text(t, ['text']))
		
	inferred, word_count = infer_word_sentiments(codes, tweetText)
	return inferred, word_count

def top_n_words(sentiment_score_dict, n, direction =1):
	if direction == 0:	
		return sorted(sentiment_score_dict.items(), key=itemgetter(1))[:n]	
	else:
		return sorted(sentiment_score_dict.items(), key=itemgetter(1), reverse = True)[:n]




def build_word_cloud_list(sentiment_score_dict, n, filename, direction =1, word_count = 0):
	
	top_n = top_n_words(sentiment_score_dict, n, direction)
	
	wfile = open("./" + filename + '.txt', 'w')
	for word in top_n:
		if type(word_count) == type({}):
			wordCount = word_count[word[0]]
		else:
			wordCount = 0
			
		for i in range((int)(abs(word[1])*10) + ( wordCount* 10)):
			wfile.write(word[0] +" ")
		wfile.write("\n")	
	wfile.close()
	




def score_sentiments(codes, tweets, direction = 1, filename = None, n = 10):
	
	sentiment_codes, word_count = infer_tweet_word_sentiments(codes, tweets)
	
	print top_n_words(sentiment_codes, n, direction)
	
	if filename != None:
			build_word_cloud_list(sentiment_codes, n, filename, direction, word_count)








