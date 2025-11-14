import pygame
import sys
import csv
import numpy as np

#------ Settings ------
screen_l = 1068
screen_h = 768
tile_size = 32
running = True
path_csv = "MapaTeste_Chao.csv"
path_menu = "MenuImagem.png"
relogio = pygame.time.Clock()
FPS = 60
gameName = "Medieval Tower Defense"
state = "Menu"


#------ Pygame Init ------
pygame.init()
pygame.font.init()

pygame.display.set_caption(gameName)
screen = pygame.display.set_mode((screen_l, screen_h))
fonte = pygame.font.Font('Fonte.ttf', 72)

superficie = pygame.Surface((screen_l, screen_h))
superficie.fill((100, 100, 100))



#------ Images Load ------
sheet = pygame.image.load("FieldsTileset.png").convert_alpha()

#------ Methods ------
def Menu():
    global state
    global running
    fonte_botoes = pygame.font.Font('Fonte.ttf', 60)
    superficieMenu = pygame.Surface((screen_l, screen_h))
    menuImage = pygame.image.load("MenuImagem.png").convert()
    menuImage = pygame.transform.scale(menuImage, (screen_l, screen_h))
    
    Button1 = pygame.Rect(380, 250, 300, 100)
    Button2= pygame.Rect(380, 375, 300, 100)
    Button3= pygame.Rect(380, 500, 300, 100)
    
    superficieMenu.blit(menuImage, (0, 0))
    
    #Title missing
    title = fonte.render("Medieval Tower Defense", True, (0,0,0))
    superficieMenu.blit(title, (150, 75))
    
    
    Button1 = pygame.draw.rect(superficieMenu, (139, 69, 19), Button1, border_radius= 10)
    Button2 = pygame.draw.rect(superficieMenu, (139, 69, 19), Button2, border_radius= 10)
    Button3 = pygame.draw.rect(superficieMenu, (139, 69, 19), Button3, border_radius = 10)
    
    play = fonte_botoes.render("Play", True, (245, 222, 179))
    leave = fonte_botoes.render("Leave", True, (245, 222, 179))
    settings = fonte_botoes.render("Settings", True, (245, 222, 179))
    superficieMenu.blit(play, (Button1.x + 85, Button1.y + 15))
    superficieMenu.blit(leave, (Button2.x + 70, Button2.y + 15))
    superficieMenu.blit(settings, (Button3.x + 55, Button3.y + 15))
    
    mousePosition = pygame.mouse.get_pos()
       
    if Button1.collidepoint(mousePosition) and pygame.mouse.get_pressed()[0] == 1:
            state = "Level1"
            return state
    elif Button2.collidepoint(mousePosition) and pygame.mouse.get_pressed()[0] == 1:
            running = False
            return running
    elif Button3.collidepoint(mousePosition) and pygame.mouse.get_pressed()[0] == 1:
            print("Oi") #Here is the Setting function part that I´ll create if i have time
        
    screen.blit(superficieMenu, (0,0))
        
def load_mapa(path):
    with open(path, newline='') as f: #Opens CSV File
        reader = csv.reader(f) #Reads CSV File
        data = list(reader) #creates a list called data that contains all rows from the CSV file
        numCols = len(data) # data lenght
        numRows = len(data[0])   
    global game_map_layer0 # This variable will be used outside the function, then i used global
    game_map_layer0 = np.zeros((numRows, numCols)) #Creation of a array 2D (Matrix)
    
    with open(path, 'r') as f: #Open CSV file only to read ('r')
        l = 0
        for line in f:
            values = line.split(",") # returns a array
            game_map_layer0[:,l] = np.array(values) #[:,l] All Rows, from l columns. Numpy Creates a array float64, so it puts a dot in each number 
            l += 1

def slicing(sheet, tile_size):
    largura = sheet.get_width() #get sheet width
    altura = sheet.get_height() #get sheet height
    tiles = [] #New list to receive map tiles
    for y in range(0, altura, tile_size):
        for x in range(0, largura, tile_size):
            square = sheet.subsurface(pygame.Rect(x, y, tile_size, tile_size)) #Cut the image to 32x32 pixels.
            tiles.append(square)
    return tiles


load_mapa(path_csv)

tiles = slicing(sheet, tile_size) #list that receives the cutted tiles, and after to draw game map

def Level1():
    for y in range(0, game_map_layer0.shape[0]):
        for x in range(game_map_layer0.shape[1]):
            superficie.blit(tiles[int(game_map_layer0[y, x])], (y * tile_size, x * tile_size)) #Draw superficie
    screen.blit(superficie, (0,0))        
#------ Game Loop ------
while running:
    relogio.tick(FPS)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
    
    if state == "Menu":
        Menu()
    elif state == "Level1":
        Level1() 
        
    pygame.display.flip()
pygame.quit()
sys.exit()