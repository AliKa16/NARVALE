import pandas as pd
from geopy.distance import geodesic

city_coordinates = {
    'Bangalore': (12.9716, 77.5946),
    'Pune': (18.5204, 73.8567),
    'New Delhi': (28.6139, 77.2090),
}

def calculate_distance(city1, city2):
    if city1 in city_coordinates and city2 in city_coordinates:
        return geodesic(city_coordinates[city1], city_coordinates[city2]).kilometers
    return float('inf') 

data = pd.read_csv('processed_data.csv')

desired_skills = ['Java', 'Django']
reference_city = 'Bangalore'
reference_age = 30

def filter_by_skills(row, skills):
    hard_skills = str(row['HardSkills']).split(', ') if pd.notna(row['HardSkills']) else []
    return any(skill in hard_skills for skill in skills)

data['DistanceFromReference'] = data['City'].apply(lambda x: calculate_distance(reference_city, x))

data['AgeDifference'] = abs(data['Age'] - reference_age)

filtered_data = data[data.apply(filter_by_skills, skills=desired_skills, axis=1)]

filtered_data['NormalizedDistance'] = filtered_data['DistanceFromReference'] / filtered_data['DistanceFromReference'].max()
filtered_data['NormalizedAgeDifference'] = filtered_data['AgeDifference'] / filtered_data['AgeDifference'].max()

skill_weight = 0.5
distance_weight = 0.3
age_weight = 0.2

filtered_data['Score'] = (
    skill_weight * 1 + 
    distance_weight * (1 - filtered_data['NormalizedDistance']) + 
    age_weight * (1 - filtered_data['NormalizedAgeDifference'])  
)

sorted_data = filtered_data.sort_values(by='Score', ascending=False)
sorted_data.to_csv('best_employees.csv', index=False)

print(sorted_data.head().to_string())