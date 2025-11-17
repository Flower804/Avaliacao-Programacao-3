import pygame

pygame.init()
class Enemy(pygame.sprite.Sprite):
    def __init__(self, image, pos):
        pygame.sprite.Sprite.__init__(self)
        self.image = image
        self.position = pos
        self.rect = self.image.get_rect()
        self.rect.center = pos
    def move(self): 
        self.rect.x += 1
        