from openai import OpenAI

api_key = ""  # API_KAY
model = "gpt-3.5-turbo"  
project_description = "stworzenie strony sklepu internetowego z ozdobami do domu"
menager = "IT" # Wrigth your manager category

client = OpenAI(api_key=api_key)

def get_project_requirements(api_key, model, project_description):

    response = client.chat.completions.create(model=model,
    messages=[
        {"role": "system", "content": f"Jesteś managerem {menager} w firmie informatycznej"},
        {
            "role": "user",
            "content": f"Na podstawie celu projektu informatycznego jakim jest {project_description}, napisz jakie są potrzebne umiejętności miękkie i twarde do realizacji projektu oraz ile osób powinno być w zespole."
        }
    ])

    answer = response.choices[0].message.content
    return answer


requirements = get_project_requirements(api_key, model, project_description)
print(requirements)
