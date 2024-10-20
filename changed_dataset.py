import pandas as pd
from transformers import pipeline

df = pd.read_csv('Employee_Dataset.csv')
ner_pipeline = pipeline("ner", grouped_entities=True)

def extract_hard_skills(sentence):

    result = ner_pipeline(sentence)
    skills = [entity['word'] for entity in result if entity['entity_group'] == 'MISC']    
    return ', '.join(skills)
    

print(df.head())
