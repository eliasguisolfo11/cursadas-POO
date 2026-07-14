from person import Persona
from person_overridden import Persona as PersonaS

print("=" * 60)
print("PARTE 1: Métodos heredados de object (sin sobrescribir)")
print("=" * 60)

p1 = Persona("Juan", 25, "juan@mail.com")
p2 = Persona("Juan", 25, "juan@mail.com")
p3 = Persona("Ana", 30, "ana@mail.com")

print("\n--- Imprimir objeto directamente ---")
print(p1)

print("\n--- str(persona) ---")
print(str(p1))

print("\n--- repr(persona) ---")
print(repr(p1))

print("\n--- Comparar con == ---")
print(f"p1 == p2 (mismos datos): {p1 == p2}")
print(f"p1 == p3 (distintos datos): {p1 == p3}")

print("\n--- hash() ---")
print(f"hash(p1): {hash(p1)}")
print(f"hash(p2): {hash(p2)}")
print(f"hash(p3): {hash(p3)}")

print("\n--- type() ---")
print(type(p1))

print("\n--- dir() (métodos heredados de object) ---")
print(dir(p1))

print("\n" + "=" * 60)
print("PARTE 2: Métodos sobrescritos")
print("=" * 60)

p4 = PersonaS("Juan", 25, "juan@mail.com")
p5 = PersonaS("Juan", 25, "juan@mail.com")
p6 = PersonaS("Ana", 30, "ana@mail.com")

print("\n--- str() después de sobrescribir ---")
print(str(p4))

print("\n--- repr() después de sobrescribir ---")
print(repr(p4))

print("\n--- Comparar con == después de sobrescribir __eq__ ---")
print(f"p4 == p5 (mismos datos): {p4 == p5}")
print(f"p4 == p6 (distintos datos): {p4 == p6}")

print("\n--- hash() después de sobrescribir ---")
print(f"hash(p4): {hash(p4)}")
print(f"hash(p5): {hash(p5)}")
print(f"hash(p6): {hash(p6)}")

print(f"\np4 y p5 tienen mismo hash: {hash(p4) == hash(p5)}")
print(f"p4 == p5: {p4 == p5}")

print("\n--- Usar en un set (funciona por __hash__ y __eq__) ---")
conjunto = {p4, p5, p6}
print(f"Set con p4, p5, p6 tiene {len(conjunto)} elemento(s)")

print("\n--- Usar en un dict ---")
d = {p4: "valor1", p6: "valor2"}
print(f"Dict con p4 y p6: {d}")
print(f"d.get(p5): {d.get(p5)}")
