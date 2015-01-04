from sentiment import *
from happy_sad import *


codes = get_sentiment_codes("./data/AFINN-111.txt")

tweets = read_tweets("tweet_data/10.27.tweets.txt")

#tweetText = []
#for t in tweets:
#	tweetText.append(text(t, ['text']))

#new_word_sentiments = infer_word_sentiments(codes, tweetText)
#print top_n_words(new_word_sentiments, 10)

#build_word_cloud_list(new_word_sentiments, 10, "word_cloud_list", direction =1)

score_sentiments(codes, tweets, direction = 1, filename = "word_cloud_list", n = 10)








