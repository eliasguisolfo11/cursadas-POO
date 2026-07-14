# Preguntas para responder

## 1. ¿Qué imprime el objeto antes de sobrescribir `__str__()`?

Imprime algo como `<__main__.Persona object at 0x...>`, que es la representación por defecto de `object`. Es el resultado de `__repr__()` heredado de `object`, que muestra la clase y la dirección de memoria del objeto.

---

## 2. ¿Qué cambia después de implementar `__str__()`?

Ahora `print(persona)` y `str(persona)` muestran una representación legible y amigable para el usuario, como `Persona(nombre=Juan, edad=25, email=juan@mail.com)` en lugar de la dirección de memoria.

---

## 3. ¿Por qué `p1 == p2` primero da `False`?

Porque `==` invoca `__eq__()`, que heredado de `object` compara **identidad** (si son el mismo objeto en memoria), no igualdad de valores. Como `p1` y `p2` son dos objetos distintos creados con `Persona(...)`, ocupan posiciones distintas en memoria, por lo tanto `p1 is p2` es `False` y `p1 == p2` también.

---

## 4. ¿Por qué después puede dar `True`?

Porque al sobrescribir `__eq__()` se cambia el criterio de comparación: ahora compara los **atributos** (`name`, `age`, `email`) en lugar de la identidad del objeto. Si dos personas tienen los mismos datos, `__eq__()` retorna `True`.

---

## 5. ¿Qué relación tienen `__eq__()` y `__hash__()`?

En Python, si dos objetos son iguales (`__eq__()` retorna `True`), **deben** tener el mismo `__hash__()` (contrato de hash). Si sobrescribís `__eq__` sin sobrescribir `__hash__`, Python deshabilita el hash (lo pone en `None`) y el objeto no se puede usar en sets ni como key de un dict. La regla es: misma igualdad → mismo hash.

---

## 6. ¿Cuál sería el equivalente de `getClass()` de Java en Python?

En Java: `obj.getClass()` retorna el objeto `Class`.

En Python: `type(obj)` retorna el tipo/clase del objeto. Ejemplo:

```python
type(p1)  # <class 'person.Persona'>
```

También se puede usar `p1.__class__`, que es lo más cercano a `getClass()` de Java.
