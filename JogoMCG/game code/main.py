import pygame
import sys
import csv
import numpy as np
from Enemy import Enemy

#------ Settings ------
screen_w = 1068
screen_h = 768
tile_size = 32

path_csv = "files/MapaTeste_Chao.csv"
path_menu = "assets/images/MenuImagem.png"
gameName = "Medieval Tower Defense"
state = "Menu"
running = True
FPS = 60

#------ Pygame Init ------
pygame.init()
pygame.font.init()
clock = pygame.time.Clock()

pygame.display.set_caption(gameName)

screen = pygame.display.set_mode((screen_w, screen_h))

font = pygame.font.Font('files/Fonte.ttf', 72)
buttonsFont = pygame.font.Font('files/Fonte.ttf', 60)
title = font.render(gameName, True, (0,0,0))

surface = pygame.Surface((screen_w, screen_h))
surface.fill((100, 100, 100))

#------ Images Load ------
sheet = pygame.image.load("assets/images/FieldsTileset.png").convert_alpha()
enemyImage = pygame.image.load("assets/images/tile000.png").convert_alpha()

#------ Characters Init ------
enemy_group = pygame.sprite.Group()
enemy = Enemy(enemyImage, (200, 300)) #If we print this, it will say, that it is 0 groups, then we have to create a group

enemy_group.add(enemy)
#------ Methods ------
def Menu():
    global state
    global running
    
    superficieMenu = pygame.Surface((screen_w, screen_h))
    menuImage = pygame.image.load("assets/images/MenuImagem.png").convert()
    menuImage = pygame.transform.scale(menuImage, (screen_w, screen_h))
    
    Button1 = pygame.Rect(380, 250, 300, 100)
    Button2= pygame.Rect(380, 375, 300, 100)
    Button3= pygame.Rect(380, 500, 300, 100)
    
    superficieMenu.blit(menuImage, (0, 0))   
    superficieMenu.blit(title, (150, 75))
    
    Button1 = pygame.draw.rect(superficieMenu, (139, 69, 19), Button1, border_radius= 10)
    Button2 = pygame.draw.rect(superficieMenu, (139, 69, 19), Button2, border_radius= 10)
    Button3 = pygame.draw.rect(superficieMenu, (139, 69, 19), Button3, border_radius = 10)
    
    play = buttonsFont.render("Play", True, (245, 222, 179))
    leave = buttonsFont.render("Leave", True, (245, 222, 179))
    settings = buttonsFont.render("Settings", True, (245, 222, 179))
    
    superficieMenu.blit(play, (Button1.x + 85, Button1.y + 15))
    superficieMenu.blit(leave, (Button2.x + 70, Button2.y + 15))
    superficieMenu.blit(settings, (Button3.x + 55, Button3.y + 15))
    
    mousePosition = pygame.mouse.get_pos()
       
    if Button1.collidepoint(mousePosition) and pygame.mouse.get_pressed()[0] == 1:
            state = "Level1"
            print(f"Starting {state}")
            return state
    elif Button2.collidepoint(mousePosition) and pygame.mouse.get_pressed()[0] == 1:
            running = False
            print("Leaving...")
            return running
    elif Button3.collidepoint(mousePosition) and pygame.mouse.get_pressed()[0] == 1:
            print("Settings") 
            #Here is the Setting function part that I´ll create if i have time
        
    screen.blit(superficieMenu, (0,0))
        
def load_mapa(path):
    with open(path, newline='') as f: #Opens CSV File
        reader = csv.reader(f) #Reads CSV File
        data = list(reader) #creates a list called data that contains all rows from the CSV file
        numCols = len(data) # data length
        numRows = len(data[0]) # data line 0 length 

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
            surface.blit(tiles[int(game_map_layer0[y, x])], (y * tile_size, x * tile_size)) #Draw superficie
    enemy.move()
    enemy_group.draw(surface)      
    screen.blit(surface, (0,0))  

#------ Game Loop ------
while running:
    clock.tick(FPS)
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
