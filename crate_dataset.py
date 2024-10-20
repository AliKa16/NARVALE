import pandas as pd
import random

df = pd.read_csv('Employee.csv')

soft_skills = [
    "Interpersonal communication",
    "Teamwork",
    "Empathy",
    "Problem-solving",
    "Creativity",
    "Flexibility",
    "Time management",
    "Listening skills",
    "Leadership",
    "Negotiation",
    "Collaboration",
    "Critical thinking",
    "Stress management",
    "Assertiveness",
    "Emotional intelligence",
    "Adaptability to change",
    "Conflict management",
    "Motivation",
    "Organization",
    "Decision-making",
    "Networking",
    "Persuasion",
    "Presentation skills",
    "Data analysis",
    "Project management",
    "Ability to work in a diverse environment",
    "Risk management",
    "Team management",
    "Change management",
    "Strategic thinking",
    "Critical assessment skills",
]
def generate_project_description():
    technologies = ["Python", "Java", "JavaScript", "C#", "Ruby", "PHP", "SQL", "HTML", "CSS"]
    frameworks = ["Django", "Flask", "React", "Angular", "Vue", "Spring", "Ruby on Rails"]
    objectives = [
    "creating a web application", 
    "implementing a data management system", 
    "optimizing existing software", 
    "preparing data analysis", 
    "integrating various systems", 
    "creating user interfaces", 
    "developing a mobile application",
    "designing a database schema", 
    "conducting user research", 
    "developing an API", 
    "implementing security measures", 
    "building a content management system", 
    "automating testing processes", 
    "deploying applications to the cloud", 
    "creating a data visualization dashboard", 
    "enhancing system performance", 
    "building a machine learning model", 
    "creating a responsive design", 
    "developing a chatbot", 
    "implementing continuous integration and deployment (CI/CD)", 
    "writing technical documentation"
]
    return f"Project related to {random.choice(objectives)} using technology {random.choice(technologies)} and framework {random.choice(frameworks)}."
Education = ["Bachelors","Masters","PHD" ]

# ucinanie do 300 danych
# df = df.head(300)

number_of_rows=len(df)

df['ID'] = range(1, number_of_rows + 1)
df['PersonalityType'] =  [random.randint(1, 8) for _ in range(number_of_rows)]
df['ProjectDescription'] = [generate_project_description() for _ in range(number_of_rows)]
df['SoftSkill'] = [random.sample(soft_skills, random.randint(3, 6)) for _ in range(number_of_rows)]

df = df.drop(columns=['LeaveOrNot', 'EverBenched',"Gender","PaymentTier", "LeaveOrNot"])

columns = ['ID'] + [col for col in df.columns if col != 'ID']
df = df[columns]

print(df.head())

csv_file_path = 'Employee_Dataset.csv'
df.to_csv(csv_file_path, index=False, encoding='utf-8-sig')

json_file_path = 'Employee_Dataset.json'
df.to_json(json_file_path, orient='records', force_ascii=False, indent=4)