## LDTS_1102 - Pac Man
O objetivo do nosso projeto é implementar em Java uma réplica do jogo clássico do Pac-Man, de 1980, usando as funcionalidades do Lanterna.
Projeto desenvolvido por Inês Gaspar (up202007210@fe.up.pt), Marcos Aires (up202006888@fe.up.pt) e Pedro Gomes (up202006086@fe.up.pt) para a cadeira de LDTS no ano letivo 2021/2022.

### Funcionalidades Implementadas:
-**Menu Principal:** O Menu foi implementado com um design semelhante ao do Pac-Man original, com os nomes dos quatro fantasmas escritos nas suas respetivas cores e a especificação do valor de cada pellet, cada Power-Up e cada fantasma em pontos quando é comido pelo Pac-Man. Para jogar o jogo, o user deve pressionar G. Para consultar o leaderboard, o jogador pressiona L. Para fechar a aplicação, o utilizador pressiona E.

-**Leaderboard:** Implementamos um terminal próprio para o Leaderboard que mostra as 10 melhores pontuações e o nome que os jogadores que as alcançaram registaram quando jogaram o jogo. Os jogadores estão ordenados da pontuação mais alta para a mais baixa. Pressionando x, o jogador regressa ao Menu Principal.

-**Construção do Labirinto:** O Labirinto foi feito usando quatro tipos de carateres ASCII: "=" para o portão da casa dos fantasmas, "|" para paredes verticais, "-" para paredes horizontais e "+" para esquinas.

-**Colisões do Labirinto:** O Labirinto possui as suas colisões completamente implementadas, não permitindo ao Pac-Man atravessar as paredes em nenhuma direção.

-**Contagem de Pontos:** Sempre que o Pac-Man come um pellet, a sua pontuação aumenta em 10 pontos. Sempre que se alimenta de um Power Up, a pontuação aumenta em 50 pontos.

-**Buracos do Labirinto:** Quando o Pac-Man atravessa um buraco nos limites do labirinto, é teletransportado para o buraco na outra extremidade e pode continuar a sua movimentação.

-**Frutas:** Ocasionalmente, frutas irão surgir em posições aleatórias do labirinto. Comê-las dará uma quantidade de pontos superior à de um pellet normal.

-**Movimento Constante do Pac Man:** O Pac Man move-se por tempo indefinido numa direção até receber outro input no teclado ou encontrar uma parede no seu caminho. Nesta segunda situação, fica imóvel até receber um novo input.

-**Fantasmas:** Cada fantasma possui um comportamento próprio durante o jogo, definindo os seus movimentos com base na posição atual do Pac-Man e movendo-se mesmo que o Pac-Man esteja parado. Os fantasmas devem entrar em modo de fuga quando o Pac-Man consome um Power Up. Se o Pacman tocar num fantasma sem este estar no modo de fuga, o Pac Man perde uma vida. A implementação dos fantasmas foi parcial, tendo-nos deparado com alguns bugs de movimento dos fantasmas que não conseguimos corrigir antes da entrega final.

-**Power Ups:** Comer um Power Up permite ao Pac Man comer os fantasmas em que toque durante os 8 segundos seguintes. Durante a duração do Power Up, os fantasmas entram em modo de fuga, procurando fugir do Pac Man em vez de persegui-lo.


### Diagrama UML

![img_10.png](img_10.png)

### Design

![img_5.png](img_5.png)

![img_12.png](img_12.png)

![img_7.png](img_7.png)

### 1. Model-View-Controller
**Contexto do Problema:**
  
  De modo a conseguirmos ter um jogo funcional, dividimos o código todo em classes da forma que consideramos mais adequada até ao momento. Porém, é facto de que o código ainda não se encontra dividido em pacotes, sendo notório também que ainda não há uma distinção totalmente clara sobre o que algumas classes fazem (temos, por exemplo, o caso do Pacman, que se desenha a si próprio, atualiza a sua posição e ainda guarda a sua informação, nomeadamente a posição em que se encontra). Isto, porém viola o Single Responsibilty Principle, que refere que, segundo Robert C. Martin: "A class should have one, and only one, reason to change.", ou seja, a cada classe deve caber apenas uma responsabilidade específica.

**O Pattern:** 
  
  Assim, pretendemos depois implementar, visando já as entregas que se avizinham, este MVC (Model-View-Control), que se foca, acima de tudo, no princípio de que a parte de interação com o utilizador (input e apresentação) deve ser separada da parte dos dados do programa e sua manipulação. 
  
 
![image](https://user-images.githubusercontent.com/93000291/148655510-9a9865d1-886e-40c9-945e-e4802900552e.png)

 
  Como está ilustrado no esquema, este pattern consiste numa divisão tridimensional do código, em que o view diz respeito à parte de interação com o utilizador, o modelo aos dados do programa e o controlador, que manipula os dados e decide como estes vão ser mostrados. Explicando o fluxo de uma forma sucinta, o utilizador do programa faz uma solicitação ao programa, que é tratada pelo controller, que manipula o modelo de acordo com a solicitação e recebe informação do mesmo como resposta, atualizando o que está no view e que vai ser mostrado ao utilizador.
  Pretendemos aplicar este padrão visto que não fazer esta separação poderia causar alguns efeitos indesejados no nosso software. Explicitando, quantas mais responsabilidades couberem a só uma classe (ou seja caso uma classe tenha responsabilidades que digam respeito à parte do controller e à parte do view, como por exemplo acontece com a classe PacMan, que atualiza a sua posição- controller- e desenha-se -view) vai ser necessária alterá-la mais vezes do que seria preciso caso ela tivesse apenas uma responsabilidade em concreto, o que aumenta a probabilidade de efeitos secundários indesejados aquando da alteração e torna tudo mais propício a erros, o que apenas causaria mais trabalho do que deveria.
  
**Implementação:**
Não conseguimos implementar este pattern

**Impacto:**
Não conseguimos aplicar este pattern, pelo que não teve impacto no nosso código

### 2. Game programming pattern
**Contexto do Problema:**
  
  Tal como acontece na maior parte dos jogos, pretendemos também implementar um loop principal no nosso. Tal se deve ao facto de, como sabemos, um jogo não parar mesmo quando o jogador deixa de dar input à máquina, ou seja, mesmo que o jogador para de jogar, as animações do jogo continuam a atualizar, isto é, o jogo não congela, dado que os diferentes elementos de um jogo têm que ter ações independentes das ações de outros elementos.
  
**O Pattern:**  

``` java
while (true) {
            try {
                Thread.sleep(170);
                render();
                processInput(game);
                updateGhosts(game);
                if (key.getKeyType() == EOF) {
                    break;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
  ```
  
  O fragmento de código acima é o ponto onde implementamos o game loop. Este loop permite, por fases, renderizar os elementos do jogo inicialmente, receber e processar um input do jogador e atualizar os fantasmas mediante esse input. O método sleep faz a thread principal parar por 0.17 segundos para regular a velocidade do jogo.

**Implementação**

A implementação deste pattern consiste na divisão das funções principais do jogo em 3 dimensões principais: renderização, processamento de inputs e atualização. A função render() trata de, a cada mudança no jogo, limpar o screen do estado anterior, criar um novo objeto TextGraphics e desenhar os vários elementos do jogo para visualização do utilizador. Possui além disso uma condicional para quando o labirinto sofre um reload, se o pacman perder uma vida ou o labirinto anterior ficar sem pellets, o jogo parar durante um equeno intervalo de temo.A função processInput(Game game) efetua a leitura e processamento de KeyStrokes, de forma a atualizar o movimento do PacMan conforme o utilizador pressione teclas. A função updateGhosts(Game game) faz atualização do movimento dos fantasmas, que precisam ser atualizados a cada frame, usando o tempo desde que a ronda foi iniciada para ditar se os fantasmas estão em modo de Scatter ou de Chase.

```
private void render() throws IOException, InterruptedException {
        if (!Maze.alreadyExecuted) { //this is only called every time the screen is reloaded (the players eats all food or loses one life)
            TimeUnit.SECONDS.sleep(1);
            Maze.alreadyExecuted = true;
        }
        TextGraphics graphics = screen.newTextGraphics();
        screen.clear();
        gs.drawGameElements(graphics);
        maze.drawMazeElements(graphics);
        screen.refresh();
    }

    public void processInput(Game game) throws IOException, InterruptedException {
        keyRead = new Thread() {
            @Override
            public void run() {
                KeyStroke newKey = new KeyStroke(KeyType.ArrowLeft);
                try {
                    newKey = screen.readInput();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (key.getKeyType() != newKey.getKeyType()) {
                    updateKey(newKey);
                }
                else if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q')) {
                    try {
                        screen.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        keyRead.start();
        processKey(key, gs, game);
    }
  private void updateGhosts(Game game) throws IOException, InterruptedException {
        if (maze.isGhost(new Position(14, 17)) || maze.isGhost(new Position(14, 16)) || maze.isGhost(new Position(14, 15))) {
            ghostsExitHouse();
        }
        else {
            if (!countedStartTime){
                startTimeScatter = System.currentTimeMillis();
                countedStartTime = true;
            }
            elapsedTimeScatter = System.currentTimeMillis() - startTimeScatter;
            //System.out.println(elapsedTimeScatter);
            if ((elapsedTimeScatter >= 0 && elapsedTimeScatter <= 5000) || (elapsedTimeScatter >= 25000 && elapsedTimeScatter <= 30000) || (elapsedTimeScatter >= 50000 && elapsedTimeScatter <= 55000) || elapsedTimeScatter >= 75000 && elapsedTimeScatter <= 80000) {
                moveGhostsScatter();
            }
            else {
                moveGhostsChase();
            }
        }
        nonFrightenedCollisions(gs, game);
        frightenedCollisions(gs);
    }   
```
**Impacto**

A aplicação deste pattern permitiu-nos manter o código mais organizado e simples de compreender, bem como permitiu que o jogo tivesse uma velocidade adequada. Porém, em alguns computadores este pattern pode tornar o jogo mais lento do que o desejável.

### 3. Strategy pattern
**Contexto do Problema:**
  
  Ao nos depararmos com a complexidade elevada do movimento dos fantasmas no jogo, apercebemo-nos de que uma estratégia de programar para uma implementação não seria a mais adequada para a resolução do nosso problema, dado que tal abordagem não seria nada reutilizável, implicando portanto mudanças bruscas no código caso houvesse a hipotética necessidade de, por exemplo, mudar a estratégia de movimento dos fantasmas, o que levaria a uma maior probabilidade de ocorrência de falhas no código. Sendo assim, e pesando todos os prós e contras desta abordagem, chegamos a um consenso que seria de maior proveito aplicar o strategy pattern, que se alinha com o princípio de programar para uma interface, em detrimento de programar para uma implementação. Neste sentido, decidimos criar três interfaces diferentes, sendo que cada uma representa um dos tipos de movimento dos fantasmas (scatter, que corresponde a um movimento caracterizado pelo deslocamento dos fantasmas para os respetivos cantos do mapa; frightened, que corresponde ao movimento em que os fantasmas fogem do pacman e chase, em que os fantasmas perseguem o pacman). Cada uma destas interfaces tem uma ou mais classes que representam uma ou mais implementações de cada um dos modos do movimento dos fantasmas (contamos com quatro modos de scatter, um modo de chase e um modo de frightened).
  
  **O Pattern:**
  ![image](https://user-images.githubusercontent.com/93000291/151686841-8ba0f672-f206-46de-a2b3-4eea26de1457.png)
  
  **Implementação:**
  
  Como se pode observar pela imagem abaixo, temos as três interfaces (chase, scatter e frightened) e temos cada uma das classes respetivas que implementam essas interfaces e que se encarregam por tratar de cada um desses movimentos dos fantasmas

  ![image](https://user-images.githubusercontent.com/93000291/151681138-0009376f-5a36-43eb-9fad-d360a275b71a.png)

  **Impacto:**
  
  A implementação deste strategy pattern permite tornar o código mais reutilizável e permitiu-nos implementar um tipo de chase apenas para todos os fantasmas (o frightened já é o mesmo originalmente para todos, e cada fantasma no nosso jogo tem um tipo de scatter). Caso queiramos mudar a estratégia de movimento temos apenas que, em vez de mudar o código todo, manipular a interface respetiva e implementar esse outro tipo de estratégia de movimento.
  
### Code Smells Detetados e Soluções de Refactoring

#### Dead Code
Na verificação do código do nosso projeto, encontramos ocorrências de Dead Code sobre a forma de versões anteriores de código que ficaram comentadas em algumas classes. Por exemplo, após a alteração do funcionamento do método chase na classe ChaseRandomly (imagem abaixo). Esta situação torna o código desnecessariamente grande e diminui organização do código, pelo que a melhor forma de resolver o problema neste contexto será remover o código comentado, tornando o código final mais limpo e fácil de ler.


![img_9.png](img_9.png)

#### Duplicate Code
Quando analisamos o código do projeto, encontramos código repetido e redundante, em particular na classe Element tínhamos duplicado os atributos de Position, atributo que também é declarado na classe.

Fragmento do código de Position
```
private int x, y;
    private int velX = 0, velY = 0;

    public Position(int a, int b) {
        x = a;
        y = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
```

Fragmento do código de Element antes de detetarmos o code smell
```
 private int x;
    private int y;
    protected Position pos;

    public Element(int x, int y){
        pos = new Position(x, y);
    }

    public Position getPosition(){
        return pos;
    }
```

#### Switch Statements

No caso das estratégias de movimento, o nosso código possui uma combinação grande de Switch Cases e If's no código das estratégias de movimento. Apesar de não termos conseguido alterar o código de forma a resolver este code smell, o processo de refactoring que aplicaríamos se houvesse mais tempo seria subdividir cada direção de movimento (esquerda,direita, cima e baixo) de cada estratégia num método próprio da estratégia em questão e, possivelmente, aplicar novamente o Strategy Pattern a partir dessa estratégia de movimento, criando uma estratégia para cada direção. Este code smell torna-se incómodo na medida em que é complicado efetuar alterações ao código afetado por este se tornar difícil de ler e bastante longo, sendo difícil de detetar erros no meio dele. Possivelmente, os erros do movimento dos fantasmas que tivemos poderiam ter a sua correção facilitada se não fosse por este code smell.

#### Single Responsability Principle
Reparamos também que temos classes que estão encarregues de mais do que uma função específica no jogo. Por exemplo, o PacMan tem como função guardar o movimento, verificar o movimento e desenhar-se.
Uma forma de corrigir este code smell é dividir o Projeto em packages onde se separava as principais funções disponíveis no jogo.

### Testagem

![img_13.png](img_13.png)

### Auto-Avaliação

Pedro Gomes (up202006086): 33,3%

Inês Gaspar (up202007210): 33,3%

Marcos Aires (up202006888): 33,3%
