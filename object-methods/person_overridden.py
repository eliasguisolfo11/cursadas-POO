class Persona:

    def __init__(self, name, age, email):
        self.name = name
        self.age = age
        self.email = email

    def __str__(self):
        return f"Persona(nombre={self.name}, edad={self.age}, email={self.email})"

    def __repr__(self):
        return f"Persona('{self.name}', {self.age}, '{self.email}')"

    def __eq__(self, other):
        if not isinstance(other, Persona):
            return False
        return (self.name == other.name
                and self.age == other.age
                and self.email == other.email)

    def __hash__(self):
        return hash((self.name, self.age, self.email))
