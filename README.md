## LDTS_1102 - Pac Man
O objetivo do nosso projeto é implementar em Java uma réplica do jogo clássico do Pac-Man, de 1980, usando as funcionalidades do Lanterna.
Projeto desenvolvido por Inês Gaspar (up202007210@fe.up.pt), Marcos Aires (up202006888@fe.up.pt) e Pedro Gomes (up202006086@fe.up.pt).

### Funcionalidades Implementadas:
-**Menu Principal:** O Menu foi implementado com um design semelhante ao do Pac-Man original, com os nomes dos quatro fantasmas escritos nas suas respetivas cores e a especificação do valor de cada pellet, cada Power-Up e cada fantasma em pontos quando é comido pelo Pac-Man. Para jogar o jogo, o user deve pressionar G. Para consultar o leaderboard, o jogador pressiona L. Para fechar a aplicação, o utilizador pressiona E.

-**Leaderboard:** Implementamos um terminal próprio para o Leaderboard que mostra as 10 melhores pontuações e o nome que os jogadores que as alcançaram registaram quando jogaram o jogo. Os jogadores estão ordenados da pontuação mais alta para a mais baixa. Pressionando X, o jogador regressa ao Menu Principal.
-**Construção do Labirinto:** O Labirinto foi feito usando quatro tipos de carateres ASCII: "=" para o portão da casa dos fantasmas, "|" para paredes verticais, "-" para paredes horizontais e "+" para esquinas.

-**Colisões do Labirinto:** O Labirinto possui as suas colisões completamente implementadas, não permitindo ao Pac-Man atravessar as paredes em nenhuma direção.

-**Contagem de Pontos:** Sempre que o Pac-Man come um pellet, a sua pontuação aumenta em 10 pontos. Sempre que se alimenta de um Power Up, a pontuação aumenta em 50 pontos.

-**Buracos do Labirinto:** Quando o Pac-Man atravessa um buraco nos limites do labirinto, é teletransportado para o buraco na outra extremidade e pode continuar a sua movimentação.

-**Frutas:** Ocasionalmente, frutas irão surgir em posições aleatorias do labirinto. Comê-las dará uma quantidade de pontos superior à de um pellet normal.

### Funcionalidades a Implementar:
-**Fantasmas:** Cada fantasma possui um comportamento próprio durante o jogo, definindo os seus movimentos com base na posição atual do Pac-Man e movendo-se mesmo que o Pac-Man esteja parado. Os fantasmas devem entrar em modo de fuga quando o Pac-Man consome um Power Up. Se o Pacman tocar num fantasma sem este estar no modo de fuga, o Pac Man perde uma vida.

-**Power Ups:** Comer um Power Up permite ao Pac Man comer os fantasmas em que toque durante os 8 segundos seguintes. Durante a duração do Power Up, os fantasmas entram em modo de fuga, procurando fugir do Pac Man em vez de persegui-lo.

-**Movimento Constante do Pac Man:** O Pac Man move-se por tempo indefinido numa direção até receber outro input no teclado ou encontrar uma parede no seu caminho. Nesta segunda situação, fica imóvel até receber um novo input.


### Design

#### <Inserir problema encontrado aqui>
**Contexto do Problema:**
  
<Inserir Explicação do Contexto do Problema aqui>
  
**O Pattern:**
  
<Inserir Design Pattern aplicado para resolução do problema e motivo da escolha deste patter específico>
  
**Implementação:**
<Esquematização de como o Pattern foi aplicado ao programa>
**Impacto:**
<Efeitos que a aplicação do pattern teve sobre o código>

### Code Smells Detetados e Soluções de Refactoring
#### <Nome do Code Smell Detetado>
<Explicação do Code Smell e dos problemas que este causa + Processo de Resolução Aplicado>

### Testagem

### Auto-Avaliação

