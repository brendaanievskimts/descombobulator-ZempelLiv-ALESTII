# 🔡 Descombobulator de Zempel-Liv

> Trabalho prático da disciplina **Algoritmos e Estruturas de Dados II**

---

## 📖 Sobre o Problema

O **Descombobulator de Zempel-Liv** é um sistema de **anti-compressão de textos** baseado em substituição recursiva de letras. A ideia é simples: dado um arquivo de entrada comprimido contendo uma tabela de substituições, o programa expande recursivamente cada letra até que nenhuma mais possa ser substituída — podendo transformar uma entrada minúscula em um texto gigantesco.

### Como funciona

O arquivo de entrada define uma **tabela de substituições** para letras minúsculas do alfabeto. Cada letra pode ser mapeada para uma sequência de outras letras (com no máximo 1024 caracteres por substituição). Letras sem substituição são consideradas **terminais** e aparecem diretamente no resultado final.

**Exemplo de entrada:**

```
a memimomu
e mimomu
i mooo
u mimimi
o
m
```

O programa deve:

1. **Identificar a letra inicial** — a primeira letra listada no arquivo (neste caso, `a`)
2. **Calcular o tamanho do arquivo final** expandido — sem gerar o texto completo, apenas contando os caracteres terminais resultantes

Para o exemplo acima, o arquivo final teria **47 caracteres**:

```
mmmooomommmooommooommooommooomommmooommooommooo
```

---

## 💡 Solução

A solução utiliza um sistema de **equações de tamanho por letra**, evitando a expansão real do texto (que poderia ser astronomicamente grande). Para cada letra com substituição, seu tamanho final é a soma dos tamanhos de cada letra da sua sequência. Letras terminais têm tamanho 1.

O algoritmo resolve essas equações por meio de **busca recursiva com memoização**, detectando possíveis ciclos no grafo de dependências entre letras.

> ⚠️ Os resultados podem ser números muito grandes — o programa utiliza `BigInteger` do Java para suportar valores arbitrários.

---

## 🛠️ Tecnologias

- **Java** (sem bibliotecas externas)
- `BigInteger` para aritmética de precisão arbitrária
- Estruturas: `HashMap`, grafos de dependência

---

## 📂 Estrutura do Projeto

```
.
├── src/
│   └── main/
│       └── java/
│           └── com/example/
│               └── Main.java      # Código-fonte principal
├── target/                        # Gerado automaticamente pelo Maven
├── pom.xml                        # Configuração do Maven
├── README.md
```

---

## ▶️ Como Executar

**Pré-requisito:** ter o [Maven](https://maven.apache.org/) instalado.

```bash
# Compilar
mvn compile

# Executar com um arquivo de entrada
mvn exec:java -Dexec.mainClass="com.example.Main" < entrada.txt
```

Ou, se preferir compilar e executar manualmente:

```bash
# Compilar
javac src/main/java/com/example/Main.java -d target/classes

# Executar
java -cp target/classes com.example.Main < entrada.txt
```

---

## 📊 Resultados

| Caso de Teste | Letra Inicial | Tamanho Final | Tempo |
|---------------|--------------|---------------|-------|
| Exemplo       | `a`          | 47            | —     |
| ...           | ...          | ...           | ...   |

*(tabela a ser preenchida conforme os testes forem executados)*

---

## 📝 Relatório

O relatório completo do trabalho contém:

- Descrição detalhada do problema
- Ideias e estruturas utilizadas na solução
- Pseudocódigo dos algoritmos
- Dificuldades encontradas e como foram superadas
- Resultados e tempos de execução para todos os casos de teste
- Conclusões

---

## 👤 Autor

Trabalho desenvolvido para a disciplina **Algoritmos e Estruturas de Dados II**.