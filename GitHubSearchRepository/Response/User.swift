//
//  User.swift
//  GitHubSearchRepository
//
//  Created by SHIKI TAKAHASHI on 2017/11/05.
//  Copyright © 2017年 SHIKI TAKAHASHI. All rights reserved.
//

import Foundation

struct User {
    let id: Int
    let login: String
    
    init(json: Any) throws {
        guard let dictionary = json as? [String: Any] else {
            throw JSONDEcodeError.invalidFormat(json: json)
        }
        
        guard let id = dictionary["id"] as? Int else {
            throw JSONDEcodeError.missingValue(key: "id", actualValue: dictionary["id"])
        }
        
        guard let login = dictionary["login"] as? String else {
            throw JSONDEcodeError.missingValue(key: "login", actualValue: dictionary["login"])
        }
        
        self.id = id
        self.login = login
    }
}
